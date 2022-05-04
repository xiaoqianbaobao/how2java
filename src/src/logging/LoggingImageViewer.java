package src.logging;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;
import java.util.logging.*;

public class LoggingImageViewer {
    public static void main(String[] args) {
        if (System.getProperty("java.util.logging.config.class") == null && System.getProperty("java.util.logging.file") == null) {
            try {
                Logger.getLogger("com.horstmann.corejava").setLevel(Level.ALL);
                final int LOG_ROTATION_COUNT = 10;
                var handle = new FileHandler("%h/LoffingImageViewer.log", 0, LOG_ROTATION_COUNT);
                Logger.getLogger("com.horstman.corejava").addHandler(handle);
            }catch (IOException e) {
                Logger.getLogger("com.horestmann.corejava").log(Level.SEVERE, "Can't create log file handle");
            }
        }
        EventQueue.invokeLater(()->{
          var windowHandle = new ImageViewerFrame.WindowHandler();
          windowHandle.setLevel(Level.ALL);
          Logger.getLogger("com.horstmann.corejava").addHandler(windowHandle);

          var frame = new ImageViewerFrame();
          frame.setTitle("LoggerImageViewer");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          Logger.getLogger("com.horstmann.corejava").fine("Showing frame");
          frame.setVisible(true);
        });
    }

    static class ImageViewerFrame extends JFrame{
        private static final int DEFAULT_WIDTH = 300;
        private static final int DEFAULT_HEIGHT = 400;

        private JLabel label;
        private static Logger logger = Logger.getLogger("com.horstman.corejava");

        public ImageViewerFrame(){
            logger.entering("ImageViewFrame", "<ini>");
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            var menuBar = new JMenuBar();
            setJMenuBar(menuBar);

            var menu = new JMenu("File");
            menuBar.add(menu);

            var openItem = new JMenuItem("Open");
            menu.add(openItem);
            openItem.addActionListener(new FileOpenListener());

            var exitItem = new JMenuItem("Exit");
            menu.add(exitItem);
            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    logger.fine("Exiting.");
                    System.exit(0);
                }
            });
            label = new JLabel();
            add(label);
            logger.exiting("ImageViewFrame", "<init>");
        }

        private class FileOpenListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent event) {
                logger.entering("ImageViewerFrame.FileOpenListener", "actionPerformed", event);
                var chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));

                chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
                    }


                    @Override
                    public String getDescription() {
                        return "GIF IMAGES";
                    }
                });

                int r = chooser.showOpenDialog(ImageViewerFrame.this);

                if (r == JFileChooser.APPROVE_OPTION) {
                    String name = chooser.getSelectedFile().getPath();
                    logger.log(Level.FINE, "Reading file {0}", name);
                    label.setIcon(new ImageIcon(name));
                }
                else logger.fine("File open dialog canceled");
                logger.exiting("ImageViewFrame.FileOpenListener", "actionPerformed");
            }
        }

        private static class WindowHandler extends StreamHandler {
            private JFrame frame;
            public WindowHandler(){
                frame = new JFrame();
                var output = new JTextArea();
                output.setEditable(false);
                frame.setSize(200, 200);
                frame.add(new JScrollPane(output));
                frame.setFocusableWindowState(false);
                frame.setVisible(true);
                setOutputStream(new OutputStream() {
                    @Override
                    public void write(int b) throws IOException {

                    }

                    public void write(byte[] b, int off, int len) {
                        output.append(new String(b, off, len));
                    }
                });
            }

            public void publish(LogRecord record) {
                if (!frame.isVisible()) return;
                super.publish(record);
                flush();
            }
        }
    }
}
