package main.java;

import java.util.Random;

public class Constant {
    // Name-constants to represent the various game mode
    public static final int CLASSIC = 1;
    public static final int MODERN  = 2;

    public static final int EASY = 1;
    public static final int MEDIUM  = 2;
    public static final int HARD  = 3;
    public static final int CUSTOM  = 4;

    public static final int PLAYER  = 1;
    public static final int COM     = 2;

    // Name-constants to represent the seeds and cell contents
    public static final int EMPTY = 0;
    public static final int CROSS = 1;
    public static final int NOUGHT = 2;
    public static final int OBSTACLE = 3; // new Features

    // Name-constants to represent the various states of the game
    public static final int PLAYING = 0;
    public static final int DRAW = 1;
    public static final int CROSS_WON = 2;
    public static final int NOUGHT_WON = 3;

    public static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWQYZ";

    public enum Response {
        Response1,
        Response2,
        Response3,
        Response4,
        Response5,
        Response6;

        public String pesan() {
            switch (this) {
                case Response1:
                    return "COM : \"Nice Move you got there.\"";
                case Response2:
                    return "COM : \"I see what you did there.\"";
                case Response3:
                    return "COM : \"You got me there.\"";
                case Response4:
                    return "COM : \"I didn't see that coming.\"";
                case Response5:
                    return "COM : \"That's a tricky move.\"";
                case Response6:
                    return "COM : \"How about this one.\"";
                default:
                    throw new AssertionError("Unknown operations " + this);
            }
        }

        public static Response getRandomResponse() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    public enum StartDiaologCom {
        Response1,
        Response2,
        Response3;

        public String pesan() {
            switch (this) {
                case Response1:
                    return "COM : \"I think I am gonna go first.\"";
                case Response2:
                    return "COM : \"All right then, I Choose .....\"";
                case Response3:
                    return "COM : \"Let's Roll...\"";
                default:
                    throw new AssertionError("Unknown operations " + this);
            }
        }

        public static StartDiaologCom getRandomResponse() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    public enum DefeatResponse {
        Response1,
        Response2,
        Response3;

        public String pesan() {
            switch (this) {
                case Response1:
                    return "COM : \"You're so good.\"";
                case Response2:
                    return "COM : \"Congratulations.\"";
                case Response3:
                    return "COM : \"Next time, i will be the victor\"";
                default:
                    throw new AssertionError("Unknown operations " + this);
            }
        }

        public static DefeatResponse getRandomResponse() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }


    public enum VictoryResponse {
        Response1,
        Response2,
        Response3;

        public String pesan() {
            switch (this) {
                case Response1:
                    return "COM : \"See, Who says that robot can't beat human.\"";
                case Response2:
                    return "COM : \"You're doing a good job.\"";
                case Response3:
                    return "COM : \"We should do this more often. =) \"";
                default:
                    throw new AssertionError("Unknown operations " + this);
            }
        }

        public static VictoryResponse getRandomResponse() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    public enum DifficultyMessage {
        EASY,
        MEDIUM,
        HARD,
        CUSTOM;

        public String pesan() {
            switch (this) {
                case EASY:
                    return "You choose EASY game mode, i believe you just wanna to practicing";
                case MEDIUM:
                    return "You choose MEDIUM game mode, enjoy your game";
                case HARD:
                    return "You choose HARD game mode, good luck...";
                case CUSTOM:
                    return "You choose CUSTOM game mode, this gonna be fun";
                default:
                    throw new AssertionError("Unknown operations " + this);
            }
        }
    }
}
