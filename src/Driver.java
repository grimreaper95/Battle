import battlegame.ability.AbilityName;
import battlegame.battle.Battle;
import battlegame.battle.IBattle;
import battlegame.gear.Gear;
import battlegame.inventory.Armory;
import battlegame.inventory.EquipmentBag;
import battlegame.player.IPlayer;
import battlegame.player.Player;
import battlegame.random.IRandom;
import battlegame.random.RandomGenerator;
import battlegame.weapon.Weapon;

import java.util.List;
import java.util.Scanner;

/**
 * A Driver class that shows how to use the Battle program.
 */

public class Driver {
  /**
   * Public method of the Driver class that gets called when program is executed.
   *
   * @param args No specific arguments required.
   */
  public static void main(String[] args) {
    IRandom randomGenerator = new RandomGenerator();
    IPlayer player1 = new Player("Player 1", randomGenerator);
    IPlayer player2 = new Player("Player 2", randomGenerator);

    System.out.println("Players enter the Battle");
    final IBattle battle = new Battle(player1, player2);

    System.out.println("Players enter with basic abilities and empty hands");
    System.out.println(player1.getDescription());
    System.out.println(player2.getDescription());

    EquipmentBag equipmentBag = new EquipmentBag(randomGenerator);
    List<Gear> gearsInEquipmentBag = equipmentBag.getAllGearsInBag();
    System.out.println("List of gears present in equipment bag.");
    for (Gear gear : gearsInEquipmentBag) {
      System.out.println(gear);
    }

    System.out.println("Equipping gears");
    System.out.println("Equipping gear for Player 1");
    player1.eqipAllGears(equipmentBag);
    System.out.println("Equipping gear for Player 2");
    player2.eqipAllGears(equipmentBag);
    System.out.println("Player description after equipping gears");
    System.out.println(player1.getDescription());
    System.out.println(player2.getDescription());

    Armory armory = new Armory(randomGenerator);
    List<Weapon> weaponsInArmory = armory.getWeaponList();
    System.out.println("List of gears present in equipment bag.");
    for (Weapon weapon : weaponsInArmory) {
      System.out.println(weapon);
    }
    System.out.println("Equipping weapons");
    System.out.println("Equipping weapon for Player 1");
    player1.eqipWeapon(armory);
    System.out.println("Equipping weapon for Player 2");
    player2.eqipWeapon(armory);
    System.out.println("Player description after equipping weapons");
    System.out.println(player1.getDescription());
    System.out.println(player2.getDescription());
    System.out.println("Initial health of player 1: " + player1.getHealth());
    player1.getHealth();
    System.out.println("Initial health of player 2: " + player2.getHealth());
    player2.getHealth();
    int moves = 1;
    System.out.println(battle.getPlayerToMoveFirstName() + " moves first.");
    battle.startBattle();
    Scanner scanner = new Scanner(System.in);
    char choice = 'N';
    do {
      System.out.println(battle.getCurrentPlayerToMoveName() + " moves now.");
      battle.move();
      System.out.println("Can player strike ? " + battle.canPlayerStrike());
      if (!battle.canPlayerStrike()) {
        System.out.println("Going to next move as Player cannot strike");
        continue;
      }
      System.out.println("Damage done = " + battle.getActualDamage());
      System.out.println("After move " + moves++);
      System.out.println("Health of player 1: " + player1.getHealth());
      System.out.println("Health of player 2: " + player2.getHealth());
      if (!battle.isBattleOn()) {
        moves = 1;
        System.out.println(battle.getWinnerName() + " wins the battle!");
        System.out.println("Do you wanna play a rematch ? (Y / N)");
        choice = scanner.next().charAt(0);
        if (Character.toUpperCase(choice) == 'Y') {
          System.out.println("Starting rematch");
          battle.rematch();
          System.out.println("Ready for rematch");
          System.out.println("Health regained after rematch!!");
          System.out.println("Health of player 1: " + player1.getHealth());
          System.out.println("Health of player 2: " + player2.getHealth());
          System.out.println("Starting battle");
          battle.startBattle();
        } else {
          break;
        }
      }
    }
    while (true);
  }
}
