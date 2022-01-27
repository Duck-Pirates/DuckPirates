// Reference data about the game
class ReferenceData {
    int shipCost = 3000;

    int[] shipUpgrades;
    shipUpgrades = []

    Map<String, Integer> map = Stream.of(new Object[][] {
            { "cannon", 600 },
            { "armour-upgrade", 400 }, //Each armour upgrade adds a number to the max HP, up to a max max ( :D )
            { "sails-upgrade", 400 }, //Same as above, making the ship faster
            { "repair", 1000 } //Full repair (HP --> Max HP)
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}