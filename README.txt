# Stock Market Management Program
- Written in Java using Intellij Community Edition
- Terminal interface program
- Implemented Singleton & Factory design pattern

## NOT WORKING
- case 3 (printPersonMenu) & case 4 (printPersonActions)
    have not yet been tested, including menus and methods.

### Concerning Main:
- When using public static void for methods that return results in the form of System.out.println();
    many, or most were originally created with the intention of returning a boolean to show success or not.
    Not sure which is better technically.
    Currently those boolean returns would go to waste, so I removed them - for now.

### Will implement sorting methods
- sort by sharePrice
- sort by creationDate

### Need to add a list of watchlists and methods to create/remove them

### Need to check printStockList() - was thrown together
- Along with the way the ownedStock and ownedStockList map/arraylist interact with related methods

### Assert != implemented throughout Main only, but needs to be more sophisticated

### Need to create licensing (MIT)

