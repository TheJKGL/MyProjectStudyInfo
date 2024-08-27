package malakhov.shpp.breakout;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends WindowProgram {

    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 700;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Number of bricks.
     */
    private static int NUMBER_OF_BRICKS = NBRICK_ROWS * NBRICKS_PER_ROW;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static int NTURNS = 3;

    /**
     * The amount of time to pause between frames (48fps).
     */
    private static final double PAUSE_TIME = 1000.0 / 48;

    /**
     * Font style.
     */
    private static final String FONT = "Verdana-25";

    public void run() {
        builtTheBrickLines();
        makeThePaddle();
        addMouseListeners();
        startGameAndCheck();
    }

    //Create the object of paddle.
    private GRect paddle = null;

    //Start speed of ball for each axis.
    private double vx = 0;
    private double vy = 8;

    /**
     * This method creates the ball and starts moving it.
     * Also, this method checks the number of NTURNS and NUMBER_OF_BRICKS
     * in order to end the game with a victory or defeat.
     */
    private void startGameAndCheck() {
        boolean isGameStopped = false;
        while (!isGameStopped) {
            GOval ball = makeTheBall();
            waitForClick();
            add(ball);
            moveTheBall(ball);
            NTURNS--;
            if (NTURNS == 0) {
                gameLost();
                isGameStopped = true;
            } else if (NUMBER_OF_BRICKS == 0) {
                isGameStopped = true;
            }
        }
    }

    /**
     * This method responds to mouse movements in the window,
     * and based on these movements, moves the paddle in the window.
     *
     * @param e MouseEvent(mouse movement).
     */
    public void mouseMoved(MouseEvent e) {
        if (paddle != null) {

            double newX = e.getX() - paddle.getWidth() / 2.0;

            if (e.getX() > PADDLE_WIDTH / 2 && e.getX() < getWidth() - PADDLE_WIDTH / 2) {
                paddle.setLocation(newX, getHeight() - PADDLE_Y_OFFSET);
            }
        }
    }

    /**
     * This method creates a paddle that can be moved and placing it
     * in the center just above the lower edge.
     */
    public void makeThePaddle() {
        paddle = new GRect(
                (getWidth() - PADDLE_WIDTH) / 2.0,
                (getHeight() - PADDLE_Y_OFFSET),
                PADDLE_WIDTH,
                PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setFillColor(Color.BLACK);
        add(paddle);
    }

    /**
     * This method creates a ball that can be bounced and placing it
     * in the center of the window.
     *
     * @return A ball that can be bounced.
     */
    public GOval makeTheBall() {
        GOval ball = new GOval((getWidth() - BALL_RADIUS) / 2.0,
                (getHeight() - BALL_RADIUS) / 2.0,
                BALL_RADIUS,
                BALL_RADIUS);
        ball.setFilled(true);
        ball.setFillColor(Color.BLACK);
        return ball;
    }

    /**
     * This method simulates the movement of the ball
     * and its reflection from the walls and roof.
     * Also, this method checks the amount of bricks left after destruction.
     *
     * @param ball just ball that can be bounced.
     */
    private void moveTheBall(GOval ball) {

        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5))
            vx = -vx;

        while (!ballIsBelowFloor(ball)) {

            ball.move(vx, vy);

            ballBelowWalls(ball);

            ballBelowCeiling(ball);

            destroyTheBrick(ball);

            if (NUMBER_OF_BRICKS == 0) {
                gameWin();
                return;
            }

            bounceTheBall(ball);

            pause(PAUSE_TIME);
        }
        remove(ball);
    }


    /**
     * This method simulates the bounce of the ball from the paddle
     * in the opposite direction.
     *
     * @param ball The ball to bounce.
     */
    private void bounceTheBall(GOval ball) {
        GObject collider = getCollidingObject(ball);
        if (collider == paddle) {
            vy = -vy;
        }
    }

    /**
     * This method simulates the reflection of the ball from the ceiling
     * in the opposite direction
     *
     * @param ball The ball to bounce.
     */
    private void ballBelowCeiling(GOval ball) {
        if (ball.getY() <= 0) {
            vy = -vy;
        }
    }

    /**
     * This method checks the coordinates of the ball
     * to see if it is under the floor or not.
     *
     * @param ball The ball to bounce.
     * @return boolean value of the ball under the floor or not
     */
    private boolean ballIsBelowFloor(GOval ball) {
        return ball.getY() + ball.getHeight() >= getHeight();
    }

    /**
     * This method simulates the reflection of the ball from the walls
     * in the opposite direction
     *
     * @param ball The ball to bounce.
     */
    private void ballBelowWalls(GOval ball) {
        if (ball.getX() + ball.getWidth() >= getWidth()) {
            vx = -vx;
        } else if (ball.getX() <= 0) {
            vx = -vx;
        }
    }

    /**
     * This method returns the object the ball collided
     * at one of the 4 points of the ball.
     *
     * @param ball The ball to bounce.
     * @return the GObject.
     */
    private GObject getCollidingObject(GOval ball) {

        GObject[] array = new GObject[4];
        array[0] = getElementAt(ball.getX(), ball.getY());
        array[1] = getElementAt(ball.getX(), ball.getY() + BALL_RADIUS);
        array[2] = getElementAt(ball.getX() + BALL_RADIUS, ball.getY());
        array[3] = getElementAt(ball.getX() + BALL_RADIUS, ball.getY() + BALL_RADIUS);

        for (int i = 0; i < 4; i++) {
            if (array[i] != null) {
                return array[i];
            }
        }
        return null;
    }

    /**
     * This method creates a brick wall located
     * in the center of the entire window.
     */
    private void builtTheBrickLines() {
        for (int y = 0; y < NBRICK_ROWS; y++) {
            for (int x = 0; x < NBRICKS_PER_ROW; x++) {
                builtTheBrick(
                        (BRICK_WIDTH + BRICK_SEP) * x,
                        BRICK_Y_OFFSET + (BRICK_SEP + BRICK_HEIGHT) * y,
                        changeTheColor(y));
            }
        }
    }

    /**
     * This method changes the color of every two lines.
     *
     * @param y column number.
     * @return the color of each row.
     */
    private Color changeTheColor(int y) {
        Color color;
        if (y%10 < 2) {
            color = Color.RED;
            return color;
        } else if (y%10 < 4) {
            color = Color.ORANGE;
            return color;
        } else if (y%10 < 6) {
            color = Color.YELLOW;
            return color;
        } else if (y%10 < 8) {
            color = Color.GREEN;
            return color;
        } else {
            color = Color.CYAN;
            return color;
        }
    }

    /**
     * This method describes the position, size and color of each brick.
     *
     * @param x     coordinates of x-axis
     * @param y     coordinates of y-axis
     * @param color just color
     */
    private void builtTheBrick(int x, int y, Color color) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setColor(color);
        brick.setFilled(true);
        brick.setFillColor(color);
        add(brick);
    }

    /**
     * This method destroys the brick when it collides with the ball
     *
     * @param ball The ball to bounce.
     */
    private void destroyTheBrick(GOval ball) {
        GObject collider = getCollidingObject(ball);
        if (collider == null)
            return;
        if (collider != paddle) {
            remove(collider);
            NUMBER_OF_BRICKS--;
            vy = -vy;
        }
    }

    /**
     * This method creates an inscription with a lost and located it
     * in the center of the window.
     */
    private void gameLost() {
        GLabel text = new GLabel("Lost");
        text.setFont(FONT);
        text.setColor(Color.BLACK);
        add(text, (getWidth() - text.getWidth()) / 2, (getHeight() - text.getHeight()) / 2);
    }

    /**
     * This method creates an inscription with a win and located it
     * in the center of the window.
     */
    private void gameWin() {
        GLabel text = new GLabel("Win");
        text.setFont(FONT);
        text.setColor(Color.BLACK);
        add(text, (getWidth() - text.getWidth()) / 2, (getHeight() - text.getHeight()) / 2);
    }
}
