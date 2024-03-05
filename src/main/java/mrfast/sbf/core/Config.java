package mrfast.sbf.core;

import mrfast.sbf.features.dungeons.TrashHighlighter;
import mrfast.sbf.utils.Utils;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class Config extends ConfigManager {

    @Property(
            type = PropertyType.TOGGLE,
            name = "Outdated Version Notification",
            description = "Receive a chat notification when using an outdated version of Skyblock Features",
            category = "§2§rCustomization",
            subcategory = "Mod"
    )
    public boolean updateNotify = true;
    @Property(
            type = PropertyType.DROPDOWN,
            name = "Update Check Type",
            description = "Choose between Full and Beta Releases for update checks",
            category = "§2§rCustomization",
            subcategory = "Mod",
            options = {"Full Releases", "Beta Releases"}
    )
    public int updateCheckType = 0;
    @Property(
            type = PropertyType.TOGGLE,
            name = "§cDeveloper Mode",
            description = "§eDeveloper Mode§r causes more logs to happen, aswell as enabling certain debug features.",
            category = "§2§rCustomization",
            subcategory = "Mod"
    )
    public boolean developerMode = false;
    @Property(
            type = PropertyType.TOGGLE,
            name = "Use At Own Risk Features",
            description = "Toggles whether §cUse At Own Risk§r features will show inside of the config menu",
            category = "§2§rCustomization",
            subcategory = "Mod"
    )
    public boolean riskyFeatures = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Customizable Sidebar",
            description = "Make the sidebar customizable",
            category = "General",
            subcategory = "Sidebar",
            isParent = true
    )
    public boolean customSidebar = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Remove Background",
            description = "Stops the background of the sidebar from rendering",
            category = "General",
            subcategory = "Sidebar",
            parentName = "Customizable Sidebar"
    )
    public boolean removeSidebarBackground = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Red Numbers From Sidebar",
            description = "Hide the red numbers from the sidebar",
            category = "General",
            subcategory = "Sidebar",
            parentName = "Customizable Sidebar"
    )
    public boolean removeSidebarRedNumbers = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Draw Text With Shadow",
            description = "Draws the text on the sidebar with a shadow",
            category = "General",
            subcategory = "Sidebar",
            parentName = "Customizable Sidebar"
    )
    public boolean useShadowOnSidebar = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Remove Hypixel From sidebar",
            description = "Hide the www.hypixel.net the sidebar bottom",
            category = "General",
            subcategory = "Sidebar",
            parentName = "Customizable Sidebar"
    )
    public boolean hideHypixelFromSidebar = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Remove Server Id From sidebar",
            description = "Hide the §8M23D§r the sidebar",
            category = "General",
            subcategory = "Sidebar",
            parentName = "Customizable Sidebar"
    )
    public boolean hideServerFromSidebar = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Floor 2 Spawn Timers",
            description = "Adds timers in the world that show when the servents will spawn.",
            category = "§1§rDungeons",
            subcategory = "Floor 2"
    )
    public boolean floor2SpawnTimers = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Floor 3 Fire Freeze Helper",
            description = "Adds tools that make it easier to use the §5Fire Freeze Staff§r on M3/F3",
            category = "§1§rDungeons",
            subcategory = "Floor 3",
            isParent = true
    )
    public boolean fireFreezeHelper = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Floor 3 Fire Freeze Timer",
            description = "Shows a timer of when to use the §5Fire Freeze Staff",
            category = "§1§rDungeons",
            subcategory = "Floor 3",
            parentName = "Floor 3 Fire Freeze Helper"
    )
    public boolean fireFreezeTimer = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Block Early Fire Freeze",
            description = "Prevent using the §5Fire Freeze Staff§r ability before its the best time to use it.",
            category = "§1§rDungeons",
            subcategory = "Floor 3",
            parentName = "Floor 3 Fire Freeze Helper",
            risky = true
    )
    public boolean blockEarlyFireFreeze = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Salvage Protection",
            description = "Blocks clicks on high-value items inside of the salvage menu.",
            category = "§1§rDungeons",
            subcategory = "Items",
            isParent = true
    )
    public boolean salvageProtection = true;

    @Property(
            type = PropertyType.NUMBER,
            name = "Value Threshold",
            description = "Set the minimum estimated value threshold. Salvaging will be blocked if the item's value exceeds this threshold.",
            category = "§1§rDungeons",
            subcategory = "Items",
            parentName = "Salvage Protection"
    )
    public int salvageProtectionMinValue = 200000;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Box Shadow Assasins",
            description = "Draws a box around invisible shadow assasins when their sword is visible.",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous"
    )
    public boolean boxShadowAssasins = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Shadow Assassin Notify",
            description = "Notify when there is a nearby shadow assassin that's invisible based off their sword.",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous"
    )
    public boolean shadowAssassinNotify = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Quick Close Chest",
            description = "Press any key or click to close secret chest screen",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous"
    )
    public boolean quickCloseChest = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Doors",
            description = "Highlights wither door and blood doors",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous"
    )
    public boolean highlightDoors = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Disable Blood Music",
            description = "Stops the music from playing when the blood room is open",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous"
    )
    public boolean stopBloodMusic = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Click in order Terminal solver",
            description = "Highlights the correct order for the Click in order terminal.",
            category = "§1§rDungeons",
            subcategory = "Terminal Solvers",
            isParent = true
    )
    public boolean clickInOrderSolver = false;
    @Property(
            type = PropertyType.COLOR,
            name = "Current Color",
            description = "",
            category = "§1§rDungeons",
            subcategory = "Terminal Solvers",
            parentName = "Click in order Terminal solver"
    )
    public Color clickInOrderSolverCurrent = new Color(0, 60, 60);
    @Property(
            type = PropertyType.COLOR,
            name = "Next Color",
            description = "",
            category = "§1§rDungeons",
            subcategory = "Terminal Solvers",
            parentName = "Click in order Terminal solver"
    )
    public Color clickInOrderSolverNext = new Color(0, 140, 140);
    @Property(
            type = PropertyType.COLOR,
            name = "Next Next Color",
            description = "",
            category = "§1§rDungeons",
            subcategory = "Terminal Solvers",
            parentName = "Click in order Terminal solver"
    )
    public Color clickInOrderSolverNext2 = new Color(0, 250, 250);

    @Property(
            type = PropertyType.TOGGLE,
            name = "Blaze Solver",
            description = "Highlights the correct blazes to shoot.",
            category = "§1§rDungeons",
            subcategory = "Solvers"
    )
    public boolean blazeSolver = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Water Board Solver",
            description = "Highlights the correct levers to flip to solve for the water puzzle.",
            category = "§1§rDungeons",
            subcategory = "Solvers"
    )
    public boolean WaterBoardSolver = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Three Weirdo Solver",
            description = "Highlights the correct chest to solve for the riddle puzzle.",
            category = "§1§rDungeons",
            subcategory = "Solvers"
    )
    public boolean ThreeWeirdosSolver = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Teleport Pad Solver",
            description = "Highlights teleport pads that you have stepped on",
            category = "§1§rDungeons",
            subcategory = "Solvers",
            isParent = true
    )
    public boolean teleportPadSolver = false;
    @Property(
            type = PropertyType.COLOR,
            name = "Stepped On Color",
            description = "",
            category = "§1§rDungeons",
            subcategory = "Solvers",
            parentName = "Teleport Pad Solver"
    )
    public Color teleportPadSolverColor = Color.red;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Creeper Solver",
            description = "Highlights the lanterns to shoot in Creeper puzzle.",
            category = "§1§rDungeons",
            subcategory = "Solvers"
    )
    public boolean creeperSolver = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Crypt Display",
            description = "Big count of how many crypts have been killed",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous"
    )
    public boolean cryptCount = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Blessings Viewer",
            description = "Displays the current blessings in a dungeons",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous"
    )
    public boolean blessingViewer = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Bats",
            description = "Draws a box around bats to make bats easier to find",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous",
            isParent = true
    )
    public boolean highlightBats = false;

    @Property(
            type = PropertyType.COLOR,
            name = "Bat Highlight Color",
            description = "",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous",
            parentName = "Highlight Bats"
    )
    public Color highlightBatColor = Color.green;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Gifts",
            description = "Highlights with a box of where gifts are at the Jerry's workshop.",
            category = "§1§rEvents",
            subcategory = "Jerrys Workshop",
            isParent = true
    )
    public boolean presentWaypoints = true;

    @Property(
            type = PropertyType.COLOR,
            name = "Gift Highlight Color",
            description = "",
            category = "§1§rEvents",
            subcategory = "Jerrys Workshop",
            parentName = "Highlight Gifts"
    )
    public Color presentWaypointsColor = Color.yellow;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Gifts To You",
            description = "Highlights gifts that are given to you",
            category = "§1§rEvents",
            subcategory = "Jerrys Workshop",
            isParent = true
    )
    public boolean highlightSelfGifts = false;

    @Property(
            type = PropertyType.COLOR,
            name = "Gift Color§1§r",
            description = "",
            category = "§1§rEvents",
            subcategory = "Jerrys Workshop",
            parentName = "Highlight Gifts To You"
    )
    public Color selfGiftHighlightColor = Color.red;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Glacial Cave Treasure Finder",
            description = "Highlights ice treasures in the wall when inside the Glacial Cave",
            category = "§1§rEvents",
            subcategory = "Glacial Cave",
            isParent = true
    )
    public boolean icecaveHighlight = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Ice Treasure Through Walls",
            description = "§cWarning Use At Own Risk",
            category = "§1§rEvents",
            subcategory = "Glacial Cave",
            parentName = "Glacial Cave Treasure Finder",
            risky = true
    )
    public boolean icecaveHighlightWalls = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Gifting Info",
            description = "Displays your current count of unique gifts given along with the corresponding milestone achieved",
            category = "§1§rEvents",
            subcategory = "Jerrys Workshop"
    )
    public boolean showGiftingInfo = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Glacial Cave Treasure Tracker",
            description = "Tracks the items you get from ice treasures",
            category = "§1§rEvents",
            subcategory = "Jerrys Workshop"
    )
    public boolean IceTreasureTracker = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Other Player Gifts",
            description = "Stops other players gifts from rendering if not given to you",
            category = "§1§rEvents",
            subcategory = "Jerrys Workshop"
    )
    public boolean hideOtherGifts = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Gift Particles",
            description = "Stops particles from gifts from rendering.",
            category = "§1§rEvents",
            subcategory = "Jerrys Workshop"
    )
    public boolean hideGiftParticles = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Slayers",
            description = "Shows a glow effect on summoned slayers.",
            category = "Slayers",
            subcategory = "Highlight Slayers",
            isParent = true
    )
    public boolean highlightSlayers = true;
    @Property(
            type = PropertyType.COLOR,
            name = "Slayer Color",
            description = "",
            category = "Slayers",
            subcategory = "Highlight Slayers",
            parentName = "Highlight Slayers"
    )
    public Color highlightSlayerColor = Color.orange;
    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Mini-bosses",
            description = "Highlights spawned mini-bosses with a glowing effect",
            category = "Slayers",
            subcategory = "Highlight Slayers",
            parentName = "Highlight Slayers"
    )
    public boolean highlightSlayerMiniboss = true;
    @Property(
            type = PropertyType.COLOR,
            name = "Miniboss Color",
            description = "",
            category = "Slayers",
            subcategory = "Highlight Slayers",
            parentName = "Highlight Slayers"
    )
    public Color highlightSlayerMinibossColor = Color.green;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Voidgloom Stage Colors",
            description = "Changes the glow effect depending the stage of the voidgloom. §cRequires Highlight Slayers to be enabled!",
            category = "Slayers",
            subcategory = "Highlight Slayers",
            isParent = true
    )
    public boolean highlightVoidgloomColors = false;

    @Property(
            type = PropertyType.COLOR,
            name = "Hit Phase Color",
            description = "",
            category = "Slayers",
            subcategory = "Highlight Slayers",
            parentName = "Highlight Voidgloom Stage Colors"
    )
    public Color highlightVoidgloomHitPhase = Color.MAGENTA;

    @Property(
            type = PropertyType.COLOR,
            name = "Laser Phase Color",
            description = "",
            category = "Slayers",
            subcategory = "Highlight Slayers",
            parentName = "Highlight Voidgloom Stage Colors"
    )
    public Color highlightVoidgloomLaserPhase = Color.cyan;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Slayer Timer",
            description = "Shows different timers for slayers including time to spawn and kill.",
            category = "Slayers",
            subcategory = "Slayer Timer"
    )
    public boolean slayerTimer = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Slayer Kills Display",
            description = "Shows the amount of kills on your screen till next slayer spawn.",
            category = "Slayers",
            subcategory = "Misc"
    )
    public boolean slayerKillDisplay = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Thrown Beacon",
            description = "Highlights the beacon thats thrown by the enderman slayer.",
            category = "Slayers",
            subcategory = "Voidgloom",
            isParent = true
    )
    public boolean highlightBeacons = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Through Walls",
            description = "Highlights the beacon that's thrown by the enderman slayer through walls. §cWarning Use At Own Risk",
            category = "Slayers",
            subcategory = "Voidgloom",
            parentName = "Highlight Thrown Beacon",
            risky = true
    )
    public boolean highlightBeaconsThroughWalls = false;

    @Property(
            type = PropertyType.COLOR,
            name = "Beacon Highlight Color",
            description = "",
            category = "Slayers",
            subcategory = "Voidgloom",
            parentName = "Highlight Thrown Beacon"
    )
    public Color highlightBeaconsColor = Color.green;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Ender Node Tracker",
            description = "Tracks the items you get from ender nodes",
            category = "§1§rThe End",
            subcategory = "Trackers"
    )
    public boolean EnderNodeTracker = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Day Tracker",
            description = "Tracks the day in the Crystal Hollows",
            category = "Mining",
            subcategory = "Trackers"
    )
    public boolean dayTracker = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Ender Nodes",
            description = "Highlights the sparkly blocks in the end",
            category = "§1§rThe End",
            subcategory = "Mining",
            isParent = true
    )
    public boolean highlightEnderNodes = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Nodes Through the walls",
            description = "Makes the Ender Node Highlight go through walls. §cWarning Use At Own Risk",
            category = "§1§rThe End",
            subcategory = "Mining",
            parentName = "Highlight Ender Nodes",
            risky = true
    )
    public boolean highlightEnderNodesWalls = false;

    @Property(
            type = PropertyType.COLOR,
            name = "Endstone Node Color",
            description = "",
            category = "§1§rThe End",
            subcategory = "Mining",
            parentName = "Highlight Ender Nodes"
    )
    public Color highlightEnderNodesEndstoneColor = Color.magenta;

    @Property(
            type = PropertyType.COLOR,
            name = "Obsidian Node Color",
            description = "",
            category = "§1§rThe End",
            subcategory = "Mining",
            parentName = "Highlight Ender Nodes"
    )
    public Color highlightEnderNodesObiColor = new Color(0x4f024f);

    @Property(
            type = PropertyType.TOGGLE,
            name = "Dungeon Chest Profit",
            description = "Shows the estimated profit for items from chests in dungeons.",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous",
            isParent = true
    )
    public boolean dungeonChestProfit = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Include Essence",
            description = "Adds essence into the profit calculation.",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous",
            parentName = "Dungeon Chest Profit"
    )
    public boolean dungeonChestProfitEssence = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Cleaner Action Bar",
            description = "Hides Health, Mana and other attributes from action bar",
            category = "General",
            subcategory = "Health & Mana Bars",
            isParent = true
    )
    public boolean cleanerActionBar = false;
    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Health",
            description = "Hides health from action bar",
            category = "General",
            subcategory = "Health & Mana Bars",
            parentName = "Cleaner Action Bar"
    )
    public boolean hideHealthFromBar = true;
    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Mana",
            description = "Hides mana from action bar",
            category = "General",
            subcategory = "Health & Mana Bars",
            parentName = "Cleaner Action Bar"
    )
    public boolean hideManaFromBar = true;
    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Overflow Mana",
            description = "Hides overflow mana from action bar",
            category = "General",
            subcategory = "Health & Mana Bars",
            parentName = "Cleaner Action Bar"
    )
    public boolean hideOverflowManaFromBar = true;
    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Defense",
            description = "Hides defense from action bar",
            category = "General",
            subcategory = "Health & Mana Bars",
            parentName = "Cleaner Action Bar"
    )
    public boolean hideDefenseFromBar = true;
    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Dungeon Secrets",
            description = "Hides secrets from action bar",
            category = "General",
            subcategory = "Health & Mana Bars",
            parentName = "Cleaner Action Bar"
    )
    public boolean hideSecretsFromBar = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Health Bar",
            description = "Moveable Health Bar that adjusts depending on absorption and damage taken",
            category = "General",
            subcategory = "Health & Mana Bars"
    )
    public boolean HealthBar = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Mana Bar",
            description = "Moveable Mana Bar that adjusts depending on abilities and overflow mana",
            category = "General",
            subcategory = "Health & Mana Bars"
    )
    public boolean ManaBar = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Health Display",
            description = "Movable health display",
            category = "General",
            subcategory = "Health & Mana Bars"
    )
    public boolean HealthDisplay = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Speed Display",
            description = "Movable Speed display",
            category = "General",
            subcategory = "Health & Mana Bars"
    )
    public boolean SpeedDisplay = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Effective Health Display",
            description = "Movable Effective Health display",
            category = "General",
            subcategory = "Health & Mana Bars"
    )
    public boolean EffectiveHealthDisplay = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Mana Display",
            description = "Movable mana",
            category = "General",
            subcategory = "Health & Mana Bars"
    )
    public boolean ManaDisplay = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Overflow Mana Display",
            description = "Movable overflow mana display",
            category = "General",
            subcategory = "Health & Mana Bars"
    )
    public boolean overFlowManaDisplay = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Defense Display",
            description = "Movable defense display",
            category = "General",
            subcategory = "Health & Mana Bars"
    )
    public boolean DefenseDisplay = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Dungeon Secrets Display",
            description = "Movable Secrets display",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous"
    )
    public boolean SecretsDisplay = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Better Party Finder Menu",
            description = "Creates a better user interface for the dungeon party finder",
            category = "§1§rDungeons",
            subcategory = "Party Finder",
            isParent = true
    )
    public boolean betterPartyFinder = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Side Menu",
            description = "Displays the hovered party info in a separate area",
            category = "§1§rDungeons",
            subcategory = "Party Finder",
            parentName = "Better Party Finder Menu"
    )
    public boolean betterPartyFinderSideMenu = true;
    @Property(
            type = PropertyType.TOGGLE,
            name = "Block Carries",
            description = "Ignore parties that are dungeon carries",
            category = "§1§rDungeons",
            subcategory = "Party Finder",
            parentName = "Better Party Finder Menu"
    )
    public boolean betterPartyFinderNoCarry = true;
    @Property(
            type = PropertyType.TOGGLE,
            name = "Block Dupes",
            description = "Ignore parties that already have a player of your class",
            category = "§1§rDungeons",
            subcategory = "Party Finder",
            parentName = "Better Party Finder Menu"
    )
    public boolean betterPartyFinderNoDupe = true;

    @Property(
            type = PropertyType.KEYBIND,
            name = "Refresh Parties",
            description = "Refreshes the Party Finder menu so new parties show up.",
            category = "§1§rDungeons",
            subcategory = "Party Finder",
            parentName = "Better Party Finder Menu"
    )
    public int betterPartyFinderReloadKey = Keyboard.KEY_R;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Dungeon Party Display",
            description = "Shows who all is in your dungeon party, including class and class lvl",
            category = "§1§rDungeons",
            subcategory = "Party Finder",
            isParent = true
    )
    public boolean dungeonPartyDisplay = false;
    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Duplicate Class",
            description = "Highlight Duplicate Classes in the Dungeon Party Display",
            category = "§1§rDungeons",
            subcategory = "Party Finder",
            parentName = "Dungeon Party Display"
    )
    public boolean dungeonPartyDisplayDupes = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Party Finder Join Info",
            description = "Shows stats of players when they join such as, avg secrets, cata lvl, etc.",
            category = "§1§rDungeons",
            subcategory = "Party Finder"
    )
    public boolean partyFinderJoinMessages = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Non-Starred Mobs",
            description = "Prevents mobs that arent starred from rendering during the dungeon.",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous"
    )
    public boolean hideNonStarredMobs = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Starred Mobs",
            description = "Highlights starred mobs in dungeons by making them glowing.",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous",
            isParent = true
    )
    public boolean boxStarredMobs = false;
    @Property(
            type = PropertyType.COLOR,
            name = "Starred Mobs Color",
            description = "",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous",
            parentName = "Highlight Starred Mobs"
    )
    public Color boxStarredMobsColor = new Color(0xFFAA00);

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Correct Livid",
            description = "Highlights the correct livid during the f5 boss fight",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous",
            isParent = true
    )
    public boolean highlightCorrectLivid = true;
    @Property(
            type = PropertyType.COLOR,
            name = "Livid Highlight Color",
            description = "",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous",
            parentName = "Highlight Correct Livid"
    )
    public Color correctLividColor = Color.cyan;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Fairy Soul Helper",
            description = "Highlights nearby fairy souls using waypoints",
            category = "Render",
            subcategory = "Highlights",
            isParent = true
    )
    public boolean fairySoulHelper = false;
    @Property(
            type = PropertyType.COLOR,
            name = "§1§rUncollected Color",
            description = "",
            category = "Render",
            subcategory = "Highlights",
            parentName = "Fairy Soul Helper"
    )
    public Color fairySoulUnfound = Color.magenta;
    @Property(
            type = PropertyType.COLOR,
            name = "§1§rCollected Color",
            description = "",
            category = "Render",
            subcategory = "Highlights",
            parentName = "Fairy Soul Helper"
    )
    public Color fairySoulFound = Color.GREEN;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Odanta",
            description = "Highlights nearby odanta's in the Rift",
            category = "The Rift",
            subcategory = "General",
            isParent = true
    )
    public boolean highlightOdanta = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Rift Enigma Soul Helper",
            description = "Highlights nearby Enigma souls using waypoints in the Rift",
            category = "The Rift",
            subcategory = "General",
            isParent = true
    )
    public boolean riftSouls = false;
    @Property(
            type = PropertyType.COLOR,
            name = "Enigma Uncollected Color",
            description = "",
            category = "The Rift",
            subcategory = "General",
            parentName = "Rift Enigma Soul Helper"
    )
    public Color riftSoulUnfound = Color.magenta;
    @Property(
            type = PropertyType.COLOR,
            name = "Enigma Collected Color",
            description = "",
            category = "The Rift",
            subcategory = "General",
            parentName = "Rift Enigma Soul Helper"
    )
    public Color riftSoulFound = Color.GREEN;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Rift Mirrorverse Helper",
            description = "Solvers for some of the puzzles in the mirrorverse in the Rift",
            category = "The Rift",
            subcategory = "General",
            isParent = true
    )
    public boolean riftMirrorverseHelper = false;

    @Property(
            type = PropertyType.COLOR,
            name = "Main Highlight Color",
            description = "",
            category = "The Rift",
            subcategory = "General",
            parentName = "Rift Mirrorverse Helper"
    )
    public Color riftMirrorverseHelperColor = new Color(0x00FFFF);

    @Property(
            type = PropertyType.TOGGLE,
            name = "Rift Hacking Helper",
            description = "Highlights the correct numbers in the hacking gui",
            category = "The Rift",
            subcategory = "General"
    )
    public boolean riftHackingHelper = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Larva Silk Display",
            description = "Highlights where the line will be drawn when using Larva Silk.",
            category = "The Rift",
            subcategory = "General",
            isParent = true
    )
    public boolean larvaSilkDisplay = true;

    @Property(
            type = PropertyType.COLOR,
            name = "Larva Silk Block Color",
            description = "",
            category = "The Rift",
            subcategory = "General",
            parentName = "Larva Silk Display"
    )
    public Color larvaSilkBlockColor = Color.ORANGE;

    @Property(
            type = PropertyType.COLOR,
            name = "Larva Silk Line Color",
            description = "",
            category = "The Rift",
            subcategory = "General",
            parentName = "Larva Silk Display"
    )
    public Color larvaSilkLineColor = Color.CYAN;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Relic Helper",
            description = "Highlights relics in the §cSpider's Den§r using waypoints",
            category = "General",
            subcategory = "Other"
    )
    public boolean spiderRelicHelper = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Armor Bar",
            description = "Hide the armor icons above health bar",
            category = "General",
            subcategory = "Health & Mana Bars"
    )
    public boolean hideArmorBar = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Hunger Bar",
            description = "Hide the food icons above hotbar",
            category = "General",
            subcategory = "Health & Mana Bars"
    )
    public boolean hideHungerBar = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Health Hearts",
            description = "Hide the health icons above health bar",
            category = "General",
            subcategory = "Health & Mana Bars"
    )
    public boolean hideHealthHearts = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Player Nametags",
            description = "Stops player's nametags from renderering",
            category = "Render",
            subcategory = "Hide Things"
    )
    public boolean hidePlayerNametags = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Normal Fullbright",
            description = "Normal classic full bright everywhere",
            category = "Render",
            subcategory = "Fullbright"
    )
    public boolean fullbright = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Dynamic Fullbright",
            description = "Turns on Fullbright in §aCrystal Hollows§r,§aYour Island§r,§aDungeons",
            category = "Render",
            subcategory = "Fullbright",
            isParent = true
    )
    public boolean DynamicFullbright = false;

    @Property(
            type = PropertyType.SLIDER,
            name = "Enabled Value",
            description = "Value of brightness to set when in the certain locations",
            category = "Render",
            subcategory = "Fullbright",
            min = 1,
            parentName = "Dynamic Fullbright"
    )
    public int DynamicFullbrightDisabled = 100;

    @Property(
            type = PropertyType.SLIDER,
            name = "Disabled Value",
            description = "Value of brightness to set when everywhere else",
            category = "Render",
            subcategory = "Fullbright",
            min = 1,
            parentName = "Dynamic Fullbright"
    )
    public int DynamicFullbrightElsewhere = 1;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide All Nametags",
            description = "Stops all nametags from renderering",
            category = "Render",
            subcategory = "Hide Things"
    )
    public boolean hideAllNametags = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Players Near NPC's",
            description = "Disables Players from rendering near NPC's",
            category = "Render",
            subcategory = "Hide Things"
    )
    public boolean hidePlayersNearNPC = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Arrows",
            description = "Stops arrows from being rendered.",
            category = "Render",
            subcategory = "Hide Things"
    )
    public boolean hideArrows = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Treecapitator Cooldown",
            description = "Displays the cooldown for the treecapitator",
            category = "Quality of Life",
            subcategory = "Foraging",
            isParent = true
    )
    public boolean treecapCooldown = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Only show if held",
            description = "Only show cooldown if the treecap is held rather than just in hotbar.",
            category = "Quality of Life",
            subcategory = "Foraging",
            parentName = "Treecapitator Cooldown"
    )
    public boolean treecapHeld = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "On-Screen Clock",
            description = "Display a clock",
            category = "Miscellaneous",
            subcategory = "Visual"
    )
    public boolean clock = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Jerry Timer",
            description = "Shows the cooldown for spawning jerry's",
            category = "§1§rEvents",
            subcategory = "Mayor Jerry"
    )
    public boolean jerryTimer = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Use Smooth Font",
            description = "Uses a smoother font to render text. §cRequires restart",
            category = "§2§rCustomization",
            subcategory = "§1§rGui"
    )
    public boolean customFont = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "§6§lFurfSky§r Themed",
            description = "Uses §6§lFurfSky§r textures for the Gui",
            category = "§2§rCustomization",
            subcategory = "§1§rGui"
    )
    public boolean furfSkyThemed = false;

    @Property(type = PropertyType.COLOR, name = "Gui Lines", description = "", category = "§2§rCustomization", subcategory = "§1§rGui")
    public Color guiLines = new Color(0x8d8d8d);

    @Property(type = PropertyType.COLOR, name = "Selected Category Text", description = "", category = "§2§rCustomization", subcategory = "§1§rGui")
    public Color selectedCategory = new Color(0x02A9EA);

    @Property(type = PropertyType.COLOR, name = "Hovered Category Text", description = "", category = "§2§rCustomization", subcategory = "§1§rGui")
    public Color hoveredCategory = new Color(0x2CC8F7);

    @Property(type = PropertyType.COLOR, name = "Default Category Text", description = "", category = "§2§rCustomization", subcategory = "§1§rGui")
    public Color defaultCategory = new Color(0xFFFFFF);

    @Property(type = PropertyType.COLOR, name = "Feature Description Text", description = "", category = "§2§rCustomization", subcategory = "§1§rGui")
    public Color featureDescription = new Color(0xbbbbbb);

    @Property(type = PropertyType.COLOR, name = "Main Box Background", description = "", category = "§2§rCustomization", subcategory = "§1§rGui")
    public Color mainBackground = new Color(25, 25, 25, 200);

    @Property(type = PropertyType.COLOR, name = "Search Box Background", description = "", category = "§2§rCustomization", subcategory = "§1§rGui")
    public Color searchBoxBackground = new Color(120, 120, 120, 60);

    @Property(type = PropertyType.COLOR, name = "Edit Gui Text", description = "", category = "§2§rCustomization", subcategory = "§1§rGui")
    public Color editGuiText = new Color(0xFFFFFF);

    @Property(type = PropertyType.COLOR, name = "Title Text", description = "", category = "§2§rCustomization", subcategory = "§1§rGui")
    public Color titleColor = new Color(0x00FFFF);

    @Property(type = PropertyType.COLOR, name = "Version Text", description = "", category = "§2§rCustomization", subcategory = "§1§rGui")
    public Color versionColor = new Color(0xFFFFFF);

    @Property(
            type = PropertyType.TOGGLE,
            name = "Player Disguiser",
            description = "Disguises players as different things",
            category = "§2§rCustomization",
            subcategory = "Player",
            isParent = true
    )
    public boolean playerDiguiser = false;

    @Property(
            type = PropertyType.DROPDOWN,
            name = "Disguise Players As",
            category = "§2§rCustomization",
            subcategory = "Player",
            options = {"Cow", "Pig", "Sheep", "Zombie", "Jerry", "Enderman", "Giant", "Baby Player", "Monki"},
            parentName = "Player Disguiser"
    )
    public int DisguisePlayersAs = 0;

    @Property(
            type = PropertyType.TEXT,
            name = "Player Cape",
            category = "§2§rCustomization",
            description = "Paste a image url to give yourself a cape!\n§aEx. https://i.imgur.com/wHk1W6X.png (This is only visible to you)",
            subcategory = "Player"
    )
    public String playerCapeURL = "";

    @Property(
            type = PropertyType.TOGGLE,
            name = "Diana Mythological Helper",
            description = "Draw an extended line of where the Mythological burrow could be",
            category = "§1§rEvents",
            subcategory = "Diana",
            isParent = true
    )
    public boolean MythologicalHelper = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Roughly Predict Burrows",
            description = "Marks a position in the world as to where the pointed to burrow could be. §CWarning these are rough estimates, they are not to be depended on.",
            category = "§1§rEvents",
            subcategory = "Diana",
            parentName = "Diana Mythological Helper"
    )
    public boolean MythologicalHelperPrediction = false;

    @Property(
            type = PropertyType.COLOR,
            name = "Actual Line Color",
            description = "",
            category = "§1§rEvents",
            subcategory = "Diana",
            parentName = "Diana Mythological Helper"
    )
    public Color MythologicalHelperActualColor = new Color(255, 85, 85);
    @Property(
            type = PropertyType.COLOR,
            name = "Prediction Line Color",
            description = "",
            category = "§1§rEvents",
            subcategory = "Diana",
            parentName = "Diana Mythological Helper"
    )
    public Color MythologicalHelperPredictionColor = Color.WHITE;
    @Property(
            type = PropertyType.COLOR,
            name = "Next Burrow Line Color",
            description = "",
            category = "§1§rEvents",
            subcategory = "Diana",
            parentName = "Diana Mythological Helper"
    )
    public Color MythologicalHelperNextColor = Color.CYAN;
    @Property(
            type = PropertyType.COLOR,
            name = "Default Burrow Color",
            description = "",
            category = "§1§rEvents",
            subcategory = "Diana",
            parentName = "Diana Mythological Helper"
    )
    public Color MythologicalHelperDefaultColor = Color.GREEN;
    @Property(
            type = PropertyType.COLOR,
            name = "Mob Burrow Color",
            description = "",
            category = "§1§rEvents",
            subcategory = "Diana",
            parentName = "Diana Mythological Helper"
    )
    public Color MythologicalHelperMobColor = Color.RED;
    @Property(
            type = PropertyType.COLOR,
            name = "Treasure Burrow Color",
            description = "",
            category = "§1§rEvents",
            subcategory = "Diana",
            parentName = "Diana Mythological Helper"
    )
    public Color MythologicalHelperTreasureColor = new Color(0xFFAA00);

    @Property(
            type = PropertyType.TOGGLE,
            name = "Crop Counter",
            description = "Shows the amount of crops on the hoe your holding",
            category = "§1§rFarming",
            subcategory = "Garden"
    )
    public boolean Counter = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Pests",
            description = "Shows where pests are in your garden",
            category = "§1§rFarming",
            subcategory = "Garden",
            isParent = true
    )
    public boolean highlightPests = false;
    @Property(
            type = PropertyType.COLOR,
            name = "Pest Highlight Color",
            description = "",
            category = "§1§rFarming",
            subcategory = "Garden",
            parentName = "Highlight Pests"
    )
    public Color highlightPestColor = Color.red;
    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Pests Through Walls",
            description = "§cWarning Use At Own Risk",
            category = "§1§rFarming",
            subcategory = "Garden",
            parentName = "Highlight Pests",
            risky = true
    )
    public boolean highlightPestThroughWalls = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Composter Overlay",
            description = "Shows extra info inside of the composter menu",
            category = "§1§rFarming",
            subcategory = "Garden"
    )
    public boolean composterOverlay = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Trevor The Trapper Helper",
            description = "Shows the biome and location of the hunted mob",
            category = "General",
            subcategory = "Other"
    )
    public boolean trevorHelper = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Auto Accept Reparty",
            description = "Auto joins part when someone does reparty",
            category = "Quality of Life",
            subcategory = "Dungeons"
    )
    public boolean autoAcceptReparty = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Auto Reparty",
            description = "Auto does reparty when the dungeon ends",
            category = "Quality of Life",
            subcategory = "Dungeons"
    )
    public boolean autoReparty = false;
    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Far Entitys in hub",
            description = "",
            category = "Render",
            subcategory = "Hide Things"
    )
    public boolean HideFarEntity = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Damage Tint",
            description = "Makes your screen get more red the lower in health you are",
            category = "Quality of Life",
            subcategory = "Visual"
    )
    public boolean damagetint = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "NameTags",
            description = "Render better nametags in dungeons",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous"
    )
    public boolean NameTags = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Trash",
            description = "Draws a red box around items that just fill up your inventory.",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous",
            isParent = true
    )
    public boolean highlightTrash = true;

    @Property(
            type = PropertyType.BUTTON,
            name = "§eEdit Trash",
            description = "The trash list will be updated once you save the file. \nTrash is item whos Skyblock ID contains any of the entries.",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous",
            placeholder = "§cEdit Trash",
            parentName = "Highlight Trash"
    )
    public Runnable editTrash = TrashHighlighter::openTrashFile;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Dungeon Map",
            description = "Render a moveable dungeon map on screen",
            category = "§1§rDungeons",
            subcategory = "Dungeon Map",
            isParent = true
    )
    public boolean dungeonMap = false;

    @Property(
            type = PropertyType.SLIDER,
            name = "Player Head Scale",
            description = "Scale the size of the heads on the dungeon map §3(Percent)",
            category = "§1§rDungeons",
            subcategory = "Dungeon Map",
            min = 50,
            max = 150,
            parentName = "Dungeon Map"
    )
    public int dungeonMapHeadScale = 100;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Center Player on Dungeon Map",
            description = "Locks your player to the center of the dungeon map",
            category = "§1§rDungeons",
            subcategory = "Dungeon Map",
            parentName = "Dungeon Map"
    )
    public boolean dungeonMapCenter = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Render Player Names",
            description = "Draws names above the players",
            category = "§1§rDungeons",
            subcategory = "Dungeon Map",
            parentName = "Dungeon Map"
    )
    public boolean dungeonMapPlayerNames = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Blood Door Highlight",
            description = "Marks the players name red if they opened the blood door",
            category = "§1§rDungeons",
            subcategory = "Dungeon Map",
            parentName = "Dungeon Map"
    )
    public boolean dungeonMapBloodGuy = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Render Player Heads",
            description = "Adds an outline the the player heads on the dungeon map",
            category = "§1§rDungeons",
            subcategory = "Dungeon Map",
            parentName = "Dungeon Map"
    )
    public boolean dungeonMapHeads = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Quick Start",
            description = "Sends a chat message at the end of a dungeon that can be used to reparty or warp out of a dungeon",
            category = "§1§rDungeons",
            subcategory = "Miscellaneous"
    )
    public boolean quickStart = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Glowing Dungeon Teammates",
            description = "Make your teamates glow based on their class in dungeons.",
            category = "Render",
            subcategory = "1.9 Glow Effect"
    )
    public boolean glowingDungeonPlayers = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Glowing Items!",
            description = "Make items glow depending on rarity. (Requires Fast render to be off)",
            category = "Render",
            subcategory = "1.9 Glow Effect"
    )
    public boolean glowingItems = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hidden Jerry Alert",
            description = "Displays an alert when you find a hidden Jerry.",
            category = "§1§rEvents",
            subcategory = "Mayor Jerry"
    )
    public boolean hiddenJerryAlert = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Treasure Chest Solver",
            description = "Highlights the particles to look at when opening a treasure chest.",
            category = "Mining",
            subcategory = "Solvers"
    )
    public boolean treasureChestSolver = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Automaton Loot Tracker",
            description = "Tracks the loot from Automatons. Starts after a Automaton is killed",
            category = "Mining",
            subcategory = "Trackers"
    )
    public boolean AutomatonTracker = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Gemstone Tracker",
            description = "Tracks the stats from mining gemstones like Coins per hour",
            category = "Mining",
            subcategory = "Trackers"
    )
    public boolean gemstoneTracker = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Ghost Loot Tracker",
            description = "Tracks the loot gained from killing Ghosts",
            category = "Mining",
            subcategory = "Trackers"
    )
    public boolean ghostTracker = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Powder Mining Tracker",
            description = "Tracks the stats from mining gemstones like Coins per hour",
            category = "Mining",
            subcategory = "Trackers"
    )
    public boolean powderTracker = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Commissions Tracker",
            description = "Tracks your progress on commissions",
            category = "Commissions",
            subcategory = "Mining"
    )
    public boolean CommisionsTracker = true;
    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Completed Commissions",
            description = "Draws a color of your choosing behind commissions that are completed/unclaimed.",
            category = "Commissions",
            subcategory = "Mining",
            isParent = true
    )
    public boolean highlightCompletedCommissions = true;

    @Property(
            type = PropertyType.COLOR,
            name = "Completion Color",
            description = "",
            category = "Commissions",
            subcategory = "Mining",
            parentName = "Highlight Completed Commissions"
    )
    public Color highlightCompletedCommissionsColor = Color.GREEN;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Placed Cobblestone",
            description = "Highlights the cobblestone you place in crystal hollows",
            category = "Quality of Life",
            subcategory = "Mining",
            isParent = true
    )
    public boolean highlightCobblestone = false;
    @Property(
            type = PropertyType.COLOR,
            name = "Cobblestone Color",
            description = "",
            category = "Quality of Life",
            subcategory = "Mining",
            parentName = "Highlight Placed Cobblestone"
    )
    public Color highlightCobblestoneColor = Color.cyan;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Crystal Hollows Map",
            description = "Show a map of the crystal hollows",
            category = "Mining",
            subcategory = "Maps",
            isParent = true
    )
    public boolean CrystalHollowsMap = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Crystal Hollows Map Heads",
            description = "Show a heads instead of a marker on the crystal hollows map",
            category = "Mining",
            subcategory = "Maps",
            parentName = "Crystal Hollows Map"
    )
    public boolean CrystalHollowsMapHeads = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Breadcrumb Trail On Map",
            description = "Show a trail of where you have been",
            category = "Mining",
            subcategory = "Maps",
            parentName = "Crystal Hollows Map"
    )
    public boolean CrystalHollowsMapTrail = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Dwarven Mines Map",
            description = "Show a map of the dwarven map",
            category = "Mining",
            subcategory = "Maps"
    )
    public boolean dwarvenMinesMap = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Mines of Divan Metal Detector Solver",
            description = "Shows where the treasure chest is in the Mines of Divan",
            category = "Mining",
            subcategory = "Solvers"
    )
    public boolean MetalDetectorSolver = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show NPC Sell Price",
            description = "Shows the NPC Sell Price on certain items.",
            category = "Miscellaneous",
            subcategory = "Item Price Info"
    )
    public boolean showNPCSellPrice = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Crimson Isles Map",
            description = "Show a map of the Crimson Isles",
            category = "Quality of Life",
            subcategory = "Crimson Isle"
    )
    public boolean crimsonsIslesMap = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Skyblock Item ID",
            description = "Shows an items skyblock ID in the lore.",
            category = "Miscellaneous",
            subcategory = "Items"
    )
    public boolean showSkyblockID = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Fire Veil Timer",
            description = "Shows the time until the fire viel ability ends.",
            category = "Miscellaneous",
            subcategory = "Items"
    )
    public boolean fireVeilTimer = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Prevent §5Gloomlock Grimoire§r Death",
            description = "Stops the §5Gloomlock Grimoire's§r ability from killing the player by blocking the ability if §cPlayer Health < 25%",
            category = "Miscellaneous",
            subcategory = "Items"
    )
    public boolean gloomlockGrimoireProtection = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show §5Fire Freeze Staff§r Freeze Timer",
            description = "Shows a timer in the world for when the §5Fire Freeze Staff's§r ability will freeze",
            category = "Miscellaneous",
            subcategory = "Items"
    )
    public boolean fireFreezeStaffFreezeTimer = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Prehistoric Egg Distance Counter",
            description = "Shows the blocks walked on the prehistoric egg item",
            category = "Miscellaneous",
            subcategory = "Items"
    )
    public boolean prehistoricEggDistance = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show teleport overlay",
            description = "Highlights the block that your teleporting to with Aspect of the End or Aspect of the Void",
            category = "Miscellaneous",
            subcategory = "Items"
    )
    public boolean teleportDestination = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Cooldown Tracker",
            description = "Shows a display with your hotbar items cooldowns on each item",
            category = "Miscellaneous",
            subcategory = "Items",
            isParent = true
    )
    public boolean cooldownTracker = true;

    @Property(
            type = PropertyType.DROPDOWN,
            name = "Cooldown Display Type",
            description = "Draws a square behind items that are currently on\ncooldown",
            category = "Miscellaneous",
            subcategory = "Items",
            parentName = "Cooldown Tracker",
            options = {"Slot Background", "Item Bar"}
    )
    public int cooldownTrackerType = 0;

    @Property(
            type = PropertyType.COLOR,
            name = "Background Color",
            description = "",
            category = "Miscellaneous",
            subcategory = "Items",
            parentName = "Cooldown Tracker"
    )
    public Color cooldownTrackerSquareColor = Color.white;

    @Property(
            type = PropertyType.COLOR,
            name = "Bar Color",
            description = "",
            category = "Miscellaneous",
            subcategory = "Items",
            parentName = "Cooldown Tracker"
    )
    public Color cooldownTrackerBarColor = Color.cyan;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Chat Timestamps",
            description = "Add Chat Timestamps to Messages",
            category = "General",
            subcategory = "§1§rChat"
    )
    public boolean timestamps = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Better Trading",
            description = "Toggle this feature to get better trading features",
            category = "General",
            subcategory = "§1§rChat",
            isParent = true
    )
    public boolean betterTrading = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Easier trading",
            description = "Makes trading easier by typing /trade to trade with the person instead of the /tradeaccept command which expires after 30 seconds",
            category = "General",
            subcategory = "§1§rChat",
            parentName = "Better Trading"
    )
    public boolean easierTrading = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide trade expired",
            description = "Hides the message that says \"The /trade request from ... expired!\"",
            category = "General",
            subcategory = "§1§rChat",
            parentName = "Better Trading"
    )
    public boolean hideExpired = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Enchanting Solvers",
            description = "Solvers for ultrasequencer and chronomotron",
            category = "Quality of Life",
            subcategory = "Solvers"
    )
    public boolean enchantingSolvers = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide White Square",
            description = "Hide the hover highlight Square in inventories",
            category = "Quality of Life",
            subcategory = "Visual"
    )
    public boolean hideWhiteSquare = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Menu Glass",
            description = "Hide the hover text from menu glass, also blocks clicks on it.",
            category = "Quality of Life",
            subcategory = "Visual"
    )
    public boolean hideMenuGlass = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Zealot Spawn Areas & Spawn Timer",
            description = "Draws where zealots spawn and when zealots will spawn. (this includes bruisers)",
            category = "§1§rThe End",
            subcategory = "Zealots"
    )
    public boolean showZealotSpawnAreas = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Final Destination Armor Display",
            description = "Shows your final destination armor onscreen with your progress to the next upgrade.",
            category = "§1§rThe End",
            subcategory = "Armor",
            isParent = true
    )
    public boolean finalDestinationArmorDisplay = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show kills instead of percentage",
            description = "Replaces the percentage inside the display with the current number of kills / needed kills.",
            category = "§1§rThe End",
            subcategory = "Armor",
            parentName = "Final Destination Armor Display"
    )
    public boolean finalDestinationArmorDisplayKills = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Advanced Dragon Hitbox",
            description = "Draws a better hitbox for dragons. Useful for §cMaster Mode 7§r and §eDragons",
            category = "Render",
            subcategory = "Highlights",
            isParent = true
    )
    public boolean advancedDragonHitbox = true;

    @Property(
            type = PropertyType.COLOR,
            name = "Dragon Hitbox Color",
            description = "",
            category = "Render",
            subcategory = "Highlights",
            parentName = "Show Advanced Dragon Hitbox"
    )
    public Color advancedDragonHitboxColor = Color.green;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Garden Visitor Overlay",
            description = "Shows the extra information inside the Garden Visitor Gui.",
            category = "§1§rFarming",
            subcategory = "Garden"
    )
    public boolean GardenVisitorOverlay = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Blocks to Destroy Overlay",
            description = "Shows the blocks needed to destroy when clearing a plot in the garden.",
            category = "§1§rFarming",
            subcategory = "Garden"
    )
    public boolean GardenBlocksToRemove = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Make Zealots Glow",
            description = "Applys the 1.9 glow effect to zealots to make them glow and shiny.",
            category = "§1§rThe End",
            subcategory = "Zealots",
            isParent = true
    )
    public boolean glowingZealots = false;

    @Property(
            type = PropertyType.COLOR,
            name = "Zealot Color",
            description = "",
            category = "§1§rThe End",
            subcategory = "Zealots",
            parentName = "Make Zealots Glow"
    )
    public Color glowingZealotsColor = Color.MAGENTA;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Glowing Mushrooms",
            description = "Highlights glowing mushrooms in the Glowing Mushroom Cave",
            category = "§1§rFarming",
            subcategory = "Glowing Mushroom Cave",
            isParent = true
    )
    public boolean highlightMushrooms = false;

    @Property(
            type = PropertyType.COLOR,
            name = "Mushroom Highlight Color",
            description = "",
            category = "§1§rFarming",
            subcategory = "Glowing Mushroom Cave",
            parentName = "Highlight Glowing Mushrooms"
    )
    public Color highlightMushroomsColor = Color.green;

    @Property(
            type = PropertyType.TOGGLE,
            name = "1.12 Crop Hitbox",
            description = "Applys full sized hitbox for crops",
            category = "§1§rFarming",
            subcategory = "Garden"
    )
    public boolean cropBox = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Air Display",
            description = "Prevents the game from rendering the air bubbles while underwater.",
            category = "Quality of Life",
            subcategory = "Visual"
    )
    public boolean hideAirDisplay = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Geyser Particles",
            description = "Hides the annoying particles in the §6Blazing Volcano.",
            category = "Render",
            subcategory = "Hide Things"
    )
    public boolean hideGeyserParticles = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Geyser Box",
            description = "Creates a box of where the geyser area is in the §6Blazing Volcano",
            category = "Render",
            subcategory = "Highlights"
    )
    public boolean geyserBoundingBox = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "No Fire",
            description = "Removes first-person fire overlay when you are burning.",
            category = "Render",
            subcategory = "Hide Things"
    )
    public boolean noFire = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "No Hurtcam",
            description = "Removes the screen shake when you are hurt.",
            category = "Quality of Life",
            subcategory = "Visual"
    )
    public boolean noHurtcam = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Lowest BIN Price",
            description = "Shows the lowest Buy It Now price for various items in Skyblock.",
            category = "Miscellaneous",
            subcategory = "Item Price Info"
    )
    public boolean showLowestBINPrice = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Price Paid",
            description = "Shows the price you bought an item for.",
            category = "Miscellaneous",
            subcategory = "Item Price Info"
    )
    public boolean showPricePaid = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Bazaar Price",
            description = "Shows the bazaar price for various items in Skyblock.",
            category = "Miscellaneous",
            subcategory = "Item Price Info"
    )
    public boolean showBazaarPrice = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Sales Per Day",
            description = "Shows the sales per day for various items in Skyblock.",
            category = "Miscellaneous",
            subcategory = "Item Price Info"
    )
    public boolean showSalesPerDay = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Estimated Value",
            description = "Shows the Estimated Value for various items in Skyblock. Calculates using things like enchants and stars",
            category = "Miscellaneous",
            subcategory = "Item Price Info"
    )
    public boolean showEstimatedPrice = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Average BIN Price",
            description = "Shows the average Buy It Now price for various items in Skyblock.",
            category = "Miscellaneous",
            subcategory = "Item Price Info"
    )
    public boolean showAvgLowestBINPrice = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Helpful Auction Guis",
            description = "Shows the extra information about your own and others auctions.",
            category = "§1§rAuction house",
            subcategory = "Auction Utils"
    )
    public boolean auctionGuis = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Custom Auction Selling Menu",
            description = "Shows custom buttons, and inputs when selling an auction to create a better user experience.",
            category = "§1§rAuction house",
            subcategory = "Auction Utils"
    )
    public boolean customCreateAuctionGui = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Condense Item Price Info",
            description = "Only shows the things like Average BIN, Lowest BIN, Sales/Day when the Shift key is held",
            category = "Miscellaneous",
            subcategory = "Item Price Info"
    )
    public boolean showPriceInfoOnShift = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Minion Overlay",
            description = "Shows the extra information inside the minion gui.",
            category = "Miscellaneous",
            subcategory = "Minion"
    )
    public boolean minionOverlay = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Total Minion Coins Per Day",
            description = "§cRequires you to open all minions on island at least once",
            category = "Miscellaneous",
            subcategory = "Minion"
    )
    public boolean showMinionsTotalCoinsPerDay = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Last Collected Above Minion",
            description = "Shows when the minion was last collected above the minions head",
            category = "Miscellaneous",
            subcategory = "Minion"
    )
    public boolean minionLastCollected = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Quiver Overlay",
            description = "Shows the arrows in currently your quiver. §cThis will also estimate the count after arrows are shot",
            category = "Miscellaneous",
            subcategory = "Overlay",
            isParent = true
    )
    public boolean quiverOverlay = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Only show when holding bow",
            description = "",
            category = "Miscellaneous",
            subcategory = "Overlay",
            parentName = "Quiver Overlay"
    )
    public boolean quiverOverlayOnlyBow = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Low Arrow Notification",
            description = "Shows a popup on your screen if you get below §a128§r arrows.",
            category = "Miscellaneous",
            subcategory = "Overlay",
            parentName = "Quiver Overlay"
    )
    public boolean quiverOverlayLowArrowNotification = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Arrow Type",
            description = "Shows the type of arrow selected in the quiver display.",
            category = "Miscellaneous",
            subcategory = "Overlay",
            parentName = "Quiver Overlay"
    )
    public boolean quiverOverlayType = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Collections Leaderboard Overlay",
            description = "Shows a leaderboard for the collection types",
            category = "Miscellaneous",
            subcategory = "Overlay"
    )
    public boolean collectionsLeaderboard = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "SBF Trade Gui",
            description = "Shows extra information inside the trade gui, including Estimated Values",
            category = "Miscellaneous",
            subcategory = "Overlay"
    )
    public boolean tradeOverlay = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Missing Accessories",
            description = "Shows a list of what talismans your missing when in your accessory bag",
            category = "Miscellaneous",
            subcategory = "Overlay"
    )
    public boolean showMissingAccessories = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Auction Flips",
            description = "Highlights auctions that have a certain amount of profit or more.",
            category = "§1§rAuction house",
            subcategory = "Auction Utils",
            isParent = true
    )
    public boolean highlightAuctionProfit = false;
    @Property(
            type = PropertyType.NUMBER,
            name = "Margin",
            description = "Highlights auctions that have this margin",
            category = "§1§rAuction house",
            subcategory = "Auction Utils",
            parentName = "Highlight Auction Flips"
    )
    public int highlightAuctionProfitMargin = 100000;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Auctions Status",
            description = "Highlights auctions in the \"View Bids\" menu based off of if its outbid, ended, sold to someone else.",
            category = "§1§rAuction house",
            subcategory = "Auction Utils",
            isParent = true
    )
    public boolean highlightAuctionStatus = true;

    @Property(
            type = PropertyType.COLOR,
            name = "Winning Auctions",
            category = "§1§rAuction house",
            subcategory = "Auction Utils",
            parentName = "Highlight Auctions Status"
    )
    public Color winningAuctionColor = new Color(0x669bbc);

    @Property(
            type = PropertyType.COLOR,
            name = "Collect Auction",
            category = "§1§rAuction house",
            subcategory = "Auction Utils",
            parentName = "Highlight Auctions Status"
    )
    public Color collectAuctionColor = new Color(0x003049);

    @Property(
            type = PropertyType.COLOR,
            name = "Outbid Auctions",
            category = "§1§rAuction house",
            subcategory = "Auction Utils",
            parentName = "Highlight Auctions Status"
    )
    public Color outbidAuctionColor = new Color(0xe9c46a);

    @Property(
            type = PropertyType.COLOR,
            name = "Lost Auctions",
            category = "§1§rAuction house",
            subcategory = "Auction Utils",
            parentName = "Highlight Auctions Status"
    )
    public Color lostAuctionColor = new Color(0xf4a261);

    @Property(
            type = PropertyType.TOGGLE,
            name = "Auction Flipper Active",
            description = "Enables or disables the flipper with its current settings.\n§cDo not put 100% trust in the mod, it can and probably will make mistakes.",
            category = "§1§rAuction Flipper",
            subcategory = "Flipper Settings",
            isParent = true
    )
    public boolean aucFlipperEnabled = false;

    @Property(
            type = PropertyType.KEYBIND,
            name = "Keybind to toggle",
            description = "Keybind used to toggle the flipper on/off",
            category = "§1§rAuction Flipper",
            subcategory = "Flipper Settings",
            parentName = "Auction Flipper Active"
    )
    public int aucFlipperKeybind = -1;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Include BIN Flips",
            description = "Check BINs for flips. §cThis is risky you need to know what your doing.",
            category = "§1§rAuction Flipper",
            subcategory = "Flipper Settings"
    )
    public boolean aucFlipperBins = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Include Item Upgrades",
            description = "Adds value to the auctions based off of enchants, stars, drill parts, hot potato books, etc. §cThis may over-value items!",
            category = "§1§rAuction Flipper",
            subcategory = "Flipper Settings"
    )
    public boolean aucFlipperItemUpgrades = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Include Auction Flips",
            description = "Check auctions for flips",
            category = "§1§rAuction Flipper",
            subcategory = "Flipper Settings"
    )
    public boolean aucFlipperAucs = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Play sound when flip found",
            description = "",
            category = "§1§rAuction Flipper",
            subcategory = "Flipper Settings"
    )
    public boolean aucFlipperSound = true;

    @Property(
            type = PropertyType.NUMBER,
            name = "Profit Margin",
            description = "The minimum amount of profit for an auction to be shown to you.",
            category = "§1§rAuction Flipper",
            subcategory = "Flipper Settings"
    )
    public int autoAuctionFlipMargin = 200000;

    @Property(
            type = PropertyType.NUMBER,
            name = "Minimum Volume",
            description = "The minimum amount of sales per day for an auction to be shown to you.",
            category = "§1§rAuction Flipper",
            subcategory = "Flipper Settings"
    )
    public int autoAuctionFlipMinVolume = 1;

    @Property(
            type = PropertyType.NUMBER,
            name = "Minimum Flip Percent",
            description = "The minimum percent of profit from an auction to be shown to you.",
            category = "§1§rAuction Flipper",
            subcategory = "Flipper Settings"
    )
    public int autoAuctionFlipMinPercent = 5;

    @Property(
            type = PropertyType.NUMBER,
            name = "Max Amount Of Auctions",
            description = "The max amount of flips to be show to you, this will prevent lag.",
            category = "§1§rAuction Flipper",
            subcategory = "Flipper Settings"
    )
    public int autoAuctionFlipMaxAuc = 50;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Make Purse Max Amount",
            description = "Make the amount of money you can spend on an auction equal to your purse.",
            category = "§1§rAuction Flipper",
            subcategory = "Flipper Settings"
    )
    public boolean autoAuctionFlipSetPurse = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Auto Open",
            description = "Opens up the bid menu for the item with the highest profit. \n§cThis is slower than holding down key",
            category = "§1§rAuction Flipper",
            subcategory = "Flipper Settings"
    )
    public boolean autoAuctionFlipOpen = false;

    @Property(
            type = PropertyType.KEYBIND,
            name = "Open Best Flip Keybind",
            description = "Opens up the bid menu for the item with the highest profit.",
            category = "§1§rAuction Flipper",
            subcategory = "Flipper Settings"
    )
    public int autoAuctionFlipOpenKeybind = Keyboard.KEY_F;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Easy Auction Buying",
            description = "By spam clicking you will auto buy/bid the item from that is currently viewed.",
            category = "§1§rAuction house",
            subcategory = "Auction Utils"
    )
    public boolean autoAuctionFlipEasyBuy = false;

//   Auto Auction Filters

    @Property(
            type = PropertyType.CHECKBOX,
            name = "Filter Out Pets",
            description = "Filters out pets from Auto Flipper",
            category = "§1§rAuction Flipper",
            subcategory = "§1§rAuction Flipper Filter"
    )
    public boolean autoAuctionFilterOutPets = false;

    @Property(
            type = PropertyType.CHECKBOX,
            name = "Filter Out Skins",
            description = "Filters out minion skins, armor skins, and pet skins from Auto Flipper",
            category = "§1§rAuction Flipper",
            subcategory = "§1§rAuction Flipper Filter"
    )
    public boolean autoAuctionFilterOutSkins = false;

    @Property(
            type = PropertyType.CHECKBOX,
            name = "Filter Out Furniture",
            description = "Filters out furniture from Auto Flipper",
            category = "§1§rAuction Flipper",
            subcategory = "§1§rAuction Flipper Filter"
    )
    public boolean autoAuctionFilterOutFurniture = false;

    @Property(
            type = PropertyType.CHECKBOX,
            name = "Filter Out Dyes",
            description = "Filters out dyes from Auto Flipper",
            category = "§1§rAuction Flipper",
            subcategory = "§1§rAuction Flipper Filter"
    )
    public boolean autoAuctionFilterOutDyes = false;

    @Property(
            type = PropertyType.CHECKBOX,
            name = "Filter Out Runes",
            description = "Filters out runes from Auto Flipper",
            category = "§1§rAuction Flipper",
            subcategory = "§1§rAuction Flipper Filter"
    )
    public boolean autoAuctionFilterOutRunes = false;

    @Property(
            type = PropertyType.TEXT,
            name = "Blacklist",
            description = "Filters out any blacklisted items. Seperate with §a;§r.§aExample: 'bonemerang;stick'",
            category = "§1§rAuction Flipper",
            subcategory = "§1§rAuction Flipper Filter"
    )
    public String autoAuctionBlacklist = "bonemerang;soldier;jungle pick;";

    @Property(
            type = PropertyType.TOGGLE,
            name = "Granda Wolf Pet Combo Timer",
            description = "Shows time until your combo expires on the Grandma Wolf Pet",
            category = "General",
            subcategory = "Pets"
    )
    public boolean GrandmaWolfTimer = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Ad Blocker",
            description = "Hides auction/lowballing advertisements in chat",
            category = "General",
            subcategory = "§1§rChat",
            isParent = true
    )
    public boolean hideAdvertisements = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Almost All Ads",
            description = "",
            category = "General",
            subcategory = "§1§rChat",
            parentName = "Ad Blocker"
    )
    public boolean hideAdvertisementsAll = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Lowballing Ads",
            description = "",
            category = "General",
            subcategory = "§1§rChat",
            parentName = "Ad Blocker"
    )
    public boolean hideAdvertisementsLowball = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Anita Artifact Notification",
            description = "Stops '§rYour §6Anita's Artifact§r is giving you blah blah' from appearing",
            category = "General",
            subcategory = "§1§rChat"
    )
    public boolean hideAnitaArtifactNotification = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Hide Command Cooldown Warning",
            description = "Stops '§cYou are sending commands too fast!' from appearing",
            category = "General",
            subcategory = "§1§rChat"
    )
    public boolean hideSlowdownCommandWarning = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Small Items",
            description = "Makes the items you hold smaller",
            category = "General",
            subcategory = "Other"
    )
    public boolean smallItems = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Auto Party Chat",
            description = "Auto sends §a/chat p§r after joining a party §cWarning Use At Own Risk",
            category = "General",
            subcategory = "§1§rChat",
            risky = true
    )
    public boolean autoPartyChat = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "toggle sprint",
            description = "",
            category = "General",
            subcategory = "Item Fov",
            hidden = true
    )
    public boolean toggleSprint = false;

    @Property(
            type = PropertyType.NUMBER,
            name = "composterSpeedLvl",
            description = "",
            category = "General",
            subcategory = "Item Fov",
            hidden = true
    )
    public int speedLvl = 0;

    @Property(
            type = PropertyType.NUMBER,
            name = "composterMultiLvl",
            description = "",
            category = "General",
            subcategory = "Item Fov",
            hidden = true
    )
    public int multiLvl = 0;

    @Property(
            type = PropertyType.NUMBER,
            name = "composterFuelLvl",
            description = "",
            category = "General",
            subcategory = "Item Fov",
            hidden = true
    )
    public int fuelLvl = 0;

    @Property(
            type = PropertyType.NUMBER,
            name = "composterOrgLvl",
            description = "",
            category = "General",
            subcategory = "Item Fov",
            hidden = true
    )
    public int orgLvl = 0;

    @Property(
            type = PropertyType.NUMBER,
            name = "composterCostLvl",
            description = "",
            category = "General",
            subcategory = "Hidden",
            hidden = true
    )
    public int costLvl = 0;
    @Property(
            type = PropertyType.TEXT,
            name = "temporaryAuthKey",
            category = "General",
            description = "",
            subcategory = "Hidden",
            hidden = true
    )
    public String temporaryAuthKey = "";

    @Property(
            type = PropertyType.TEXT,
            name = "Mod API Url",
            category = "§eDeveloper",
            description = "§cDo not change this if you do not know what your doing!",
            subcategory = "Settings"
    )
    public String modAPIURL = "https://app.mrfast-developer.com/";

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show mob ids",
            category = "§eDeveloper",
            description = "Shows skyblock mob ids on mobs in the world using Skyblock Mob Detector",
            subcategory = "Settings"
    )
    public boolean showMobIds = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show item abilities",
            category = "§eDeveloper",
            description = "Shows when a skyblock item uses its ability, used to create features with listening for item abilities",
            subcategory = "Settings"
    )
    public boolean showItemAbilities = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Show Inspector in Guis",
            category = "§eDeveloper",
            description = "",
            subcategory = "Settings"
    )
    public boolean showInspector = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Log Debug info for Auction Flipper",
            category = "§eDeveloper",
            description = "",
            subcategory = "Settings"
    )
    public boolean debugAuctionFlipper = false;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Highlight Selected Bestiary Mobs",
            category = "Quality of Life",
            description = "Highlights mobs from the §aBestiary Menu§r in the world with glowing effect.  §eCtrl+Click§r on a mob inside the §aBestiary Menu§r to start tracking it.",
            subcategory = "Bestiary",
            isParent = true
    )
    public boolean highlightBestiaryMobs = true;

    @Property(
            type = PropertyType.TOGGLE,
            name = "Middle Click To Track",
            category = "Quality of Life",
            description = "If you middle click a mob in the world it will start being tracked.",
            subcategory = "Bestiary",
            parentName = "Highlight Selected Bestiary Mobs"
    )
    public boolean highlightBestiaryMobsMidClick = true;

    @Property(
            type = PropertyType.COLOR,
            name = "Mob Highlight Color",
            category = "Quality of Life",
            description = "",
            subcategory = "Bestiary",
            parentName = "Highlight Selected Bestiary Mobs"
    )
    public Color highlightBestiaryColor = Color.orange;

    @Property(
            type = PropertyType.TEXT,
            name = "Tracked Mobs",
            description = "This is the list of currently tracked mobs",
            category = "Quality of Life",
            subcategory = "Bestiary",
            parentName = "Highlight Selected Bestiary Mobs"
    )
    public String trackedBestiaryMobs = "";

    @Property(
            type = PropertyType.BUTTON,
            name = "§eOpen Bestiary Menu",
            description = "Opens the bestiary menu where you can select which mobs to track.",
            category = "Quality of Life",
            subcategory = "Bestiary",
            placeholder = "§cOpen Bestiary"
    )
    public Runnable openBestiary = () -> {
        Utils.GetMC().thePlayer.sendChatMessage("/bestiary");
    };

}
