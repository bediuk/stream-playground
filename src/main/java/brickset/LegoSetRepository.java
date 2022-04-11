package brickset;

import repository.Repository;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static java.util.stream.Collectors.*;

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
        getAll().stream()
                .map(name -> name.getName())
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
    /** First method
     * Returns whether there is at least one set with the word "game" in its name ignoring case.
     */
    public boolean printAnyLowerCaseMatch() {
        return getAll().stream().map(LegoSet::getName)
                .anyMatch(name -> name.toLowerCase().contains("krab"));
    }

    /** Second method
     * Prints all distinct themes that has "Duplo" name except those which has null value .
     */
    public void printFlatMapFromBrickset() {
        getAll().stream()
                .filter(brickset -> brickset.getTheme().equals("Duplo"))
                .filter(brickset ->  brickset.getTags()!=null).flatMap(brickset -> brickset.getTags().stream())
                .forEach(System.out::println);
    }
    /** Third method
     * The optional int return the biggest number of pieces among the legoset  .
     *
     */
    public Optional<String> printBiggestIntInLegoSet(){
        return getAll().stream()
            .map(LegoSet::getName)
                .reduce((piece1,piece2) -> piece1.length() >= piece2.length() ? piece1 : piece2);
    }

    /** Fourth method
     *Map returns key as a number of Piece and a Set of String which has Name of Packaging Type
     */

    public Map<Integer, Set<PackagingType>> MapsPackagingType() {
        return getAll()
                .stream()
                .collect(groupingBy(LegoSet::getPieces,mapping(LegoSet::getPackagingType, toSet())));
    }
    /** Fifth method
     * Prints a Map with SubTheme as Key of the legoset and its packaging type
     *
     */
    public Map<String,Set<PackagingType>> PrintsSubThemeWithAPackagingType(String Subtheme_Check) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getSubtheme() != null
                        && legoSet.getSubtheme().equals(Subtheme_Check))
                .collect(groupingBy(LegoSet::getName
                        ,mapping(LegoSet::getPackagingType, toSet())));}

    public static void main(String[] args) {
        System.out.println("#####First method#####");
        var repository = new LegoSetRepository();
        //System.out.println(repository.returnLegoSetWithLeastPieces());
        //repository.returnLegoSetWithSameLatters();
        //System.out.println(repository.returnHighestLegoSet());
        //repository.printLegoSetNameAndTheme();
        //repository.printLegoSetAlphabetOrder();
        if (repository.printAnyLowerCaseMatch()){
            System.out.println("exist");
        }else{
                System.out.println("doesnt exist");
            }
        System.out.println("######Second Method#####");
        repository.printFlatMapFromBrickset();
        System.out.println("#####Third Method#####");
        System.out.println(repository.printBiggestIntInLegoSet());
        System.out.println("#####Fourth Method#####");
        System.out.println(repository.MapsPackagingType());
        System.out.println("#####Fifth Method#####");
        System.out.println(repository.PrintsSubThemeWithAPackagingType("Christmas"));
    }


}
