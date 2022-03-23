package brickset;

import repository.Repository;
import java.util.Comparator;

import static java.util.stream.Collectors.toMap;
/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }


    /**
     * Returns the LEGO set with the smallest number of pieces.
     */
    public String returnLegoSetWithLeastPieces() {
        return getAll().stream()
                .min(Comparator.comparing(LegoSet::getPieces))
                .get()
                .getName();
    }

    /**
     *Returns the name of the Highest LEGO set.
     */
    public String returnHighestLegoSet() {
        return getAll().stream()
                .filter(h -> h.getDimensions() != null
                        &&
                        h.getDimensions().getHeight() != null)
                .max(Comparator.comparingDouble(x -> x.getDimensions().getHeight()))
                .get()
                .getName();
    }

    /**
     * Prints each name in which the first and the last letters are the same ignoring case.
     */
    public void returnLegoSetWithSameLatters() {
        getAll().stream().map(name -> name.getName())
                .filter(name -> Character.toLowerCase(name.charAt(0)) == Character.toLowerCase(name.charAt(name.length()-1)))
                .forEach(System.out::println);
    }

    /**
     * Prints the LEGO set names, name and their themes,and shows only first 50 of them
     *
     */
    public void printLegoSetNameAndTheme() {
        getAll().stream()
                .distinct()
                .map(x -> x.getName() + "(" + x.getTheme() +")")
                .limit(50)
                .forEach(System.out::println);
    }

    /**
     * Prints the LEGO set names sorted in alphabet order and limited to 5 to see only numbers.
     *
     */
    public void printLegoSetAlphabetOrder() {
        getAll().stream().map(LegoSet::getName)
                .sorted()
                .limit(5)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        System.out.println(repository.returnLegoSetWithLeastPieces());
        repository.returnLegoSetWithSameLatters();
        System.out.println(repository.returnHighestLegoSet());
        repository.printLegoSetNameAndTheme();
        repository.printLegoSetAlphabetOrder();
    }

}
