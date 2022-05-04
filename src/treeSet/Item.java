package treeSet;

import java.util.Objects;

public class Item implements Comparable<Item>{

    private String description;
    private int partNumber;

    public Item(String aDescription, int aPartNumber) {
        description = aDescription;
        partNumber = aPartNumber;
    }

    public String getDescription(){
        return description;
    }

    public int getPartNumber() {
        return partNumber;
    }
    public String toString() {
        return "{description=" + description + ",  partNums=" + partNumber + "}";
    }

    public boolean equals(Object otehrObjext) {
        if (this == otehrObjext) return true;
        if (otehrObjext == null) return false;
        if (getClass() != otehrObjext.getClass()) return false;
        var other = (Item) otehrObjext;
        return Objects.equals(description, other.description) && partNumber == other.partNumber;
    }

    public int hashCode() {
        return Objects.hash(description, partNumber);
    }

    @Override
    public int compareTo(Item other) {
        int diff = Integer.compare(partNumber, other.partNumber);
        return diff != 0? diff : description.compareTo(other.description);
    }
}
