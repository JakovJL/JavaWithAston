package Module_5_patterns;

interface EnemyAIStrategy {
    void execute();
}


class EnemyContext {
    private int healthPoints;
    private boolean playerIsNearby;
    private boolean hasRangedWeapon;

    public EnemyContext(int healthPoints, boolean playerIsNearby, boolean hasRangedWeapon) {
        this.healthPoints = healthPoints;
        this.playerIsNearby = playerIsNearby;
        this.hasRangedWeapon = hasRangedWeapon;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public boolean isPlayerNearby() {
        return playerIsNearby;
    }

    public boolean hasRangedWeapon() {
        return hasRangedWeapon;
    }
}

class EnemyAI {
    private EnemyAIStrategy strategy;
    private EnemyContext context;

    public EnemyAI() {
        this.context = new EnemyContext(100, false, true);
    }

    public void setStrategy(EnemyAIStrategy strategy) {
        this.strategy = strategy;
    }

    public void setContext(EnemyContext context) {
        this.context = context;
    }

    public EnemyContext getContext() {
        return context;
    }

    public void executeStrategy() {
        if (strategy != null) {
            strategy.execute();
        } else {
            System.out.println("Поведение не задано!");
        }
    }

    public void chooseStrategyBasedOnContext() {
        if (context.getHealthPoints() < 30) {
            setStrategy(new FleeStrategy());
            System.out.println("Здоровье низкое! Переключение на стратегию побега.");
        } else if (!context.isPlayerNearby() && context.hasRangedWeapon()) {
            setStrategy(new RangedAttackStrategy());
            System.out.println("Игрок далеко! Переключение на дальний бой.");
        } else if (context.getHealthPoints() > 70) {
            setStrategy(new AggressiveAttackStrategy());
            System.out.println("Здоровье высокое! Переключение на агрессивную атаку.");
        } else {
            setStrategy(new DefensiveStrategy());
            System.out.println("Переключение на оборонительную стратегию.");
        }
    }
}

class AggressiveAttackStrategy implements EnemyAIStrategy {
    @Override
    public void execute() {
        System.out.println("ИИ яростно атакует игрока!");
    }
}

class DefensiveStrategy implements EnemyAIStrategy {
    @Override
    public void execute() {
        System.out.println("ИИ ведет битву от обороны.");
    }
}

class DodgeStrategy implements EnemyAIStrategy {
    @Override
    public void execute() {
        System.out.println("ИИ уклоняется и атакует.");
    }
}

class RangedAttackStrategy implements EnemyAIStrategy {
    @Override
    public void execute() {
        System.out.println("ИИ атакует игрока с дальней дистанции!");
    }
}

class FleeStrategy implements EnemyAIStrategy {
    @Override
    public void execute() {
        System.out.println("ИИ пытается сбежать с поля боя!");
    }
}

class StealthStrategy implements EnemyAIStrategy {
    @Override
    public void execute() {
        System.out.println("ИИ скрывается в тени и готовит нападение из засады!");
    }
}


class CompositeStrategy implements EnemyAIStrategy {
    private EnemyAIStrategy[] strategies;

    public CompositeStrategy(EnemyAIStrategy... strategies) {
        this.strategies = strategies;
    }

    @Override
    public void execute() {
        System.out.println("ИИ использует комбинированную тактику:");
        for (EnemyAIStrategy strategy : strategies) {
            strategy.execute();
        }
    }
}

public class Strategy {
    public static void main(String[] args) {
        EnemyAI enemyAI = new EnemyAI();

        System.out.println("Демонстрация стратегий");
        enemyAI.setStrategy(new AggressiveAttackStrategy());
        enemyAI.executeStrategy();

        enemyAI.setStrategy(new DodgeStrategy());
        enemyAI.executeStrategy();

        enemyAI.setStrategy(new DefensiveStrategy());
        enemyAI.executeStrategy();

        enemyAI.setStrategy(new RangedAttackStrategy());
        enemyAI.executeStrategy();

        enemyAI.setStrategy(new FleeStrategy());
        enemyAI.executeStrategy();

        enemyAI.setStrategy(new StealthStrategy());
        enemyAI.executeStrategy();

        System.out.println("\nДемонстрация составной стратегии");
        enemyAI.setStrategy(new CompositeStrategy(
                new DodgeStrategy(),
                new StealthStrategy(),
                new AggressiveAttackStrategy()
        ));

        enemyAI.executeStrategy();

        System.out.println("\nДемонстрация контекстного выбора стратегии");
        // Полное здоровье, игрок далеко, есть дальнобойное оружие
        enemyAI.setContext(new EnemyContext(100, false, true));
        enemyAI.chooseStrategyBasedOnContext();
        enemyAI.executeStrategy();

        // Низкое здоровье, игрок рядом
        enemyAI.setContext(new EnemyContext(20, true, true));
        enemyAI.chooseStrategyBasedOnContext();
        enemyAI.executeStrategy();

        // Среднее здоровье, игрок рядом
        enemyAI.setContext(new EnemyContext(50, true, false));
        enemyAI.chooseStrategyBasedOnContext();
        enemyAI.executeStrategy();
    }
}