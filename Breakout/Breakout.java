/* Breakout */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60; /* was 60 */
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/** Delay */
    private static final int DELAY = 15;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
        createWall();
        createPaddle();
        makeBall();
        addMouseListeners();
        Y_VELOC = 7.0; /* sets the velocity of y to 3 */
        X_VELOC = setRandom(); /* sets x to be random */
        pause(5000);
        gameStart();
	}

    private void createWall() {
        /* draw the opening bricks */
        for (int i=0; i<NBRICK_ROWS; i++) {
            for(int j=0; j<NBRICKS_PER_ROW; j++) {
                GRect box = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
                box.setLocation(BRICK_SEP/2 + (j*BRICK_WIDTH) + (BRICK_SEP*j), BRICK_Y_OFFSET + (i*BRICK_HEIGHT) + (i*BRICK_SEP));
                box.setFilled(true);
                /* colors change every two rows */
                if(i < 2) {
                    box.setColor(Color.red);
                } else if (i < 4) {
                    box.setColor(Color.orange);
                } else if (i < 6) {
                    box.setColor(Color.yellow);
                } else if (i < 8) {
                    box.setColor(Color.green);
                } else {
                    box.setColor(Color.cyan);
                }
                add(box);
            }
        }
    }

    private void createPaddle() {
        PADDLE.setFilled(true);
        add(PADDLE, WIDTH/2-(PADDLE_WIDTH/2), HEIGHT-PADDLE_Y_OFFSET);
    }

    /** called on mouse drag to reposition the object */
    public void mouseMoved(MouseEvent e) {
        if (e != null) {
            int moveWidth = e.getX();
            PADDLE.setLocation(moveWidth-(PADDLE_WIDTH/2), HEIGHT-PADDLE_Y_OFFSET);
            /* if mouse position is less than 0, it's off the screen, set it to to the limit */
            if(moveWidth-(PADDLE_WIDTH/2) < 0) {
                PADDLE.setLocation(0, HEIGHT-PADDLE_Y_OFFSET);
            }
            /* if mouse position is higher than the application's width, it is out of bounds */
            if(moveWidth-(PADDLE_WIDTH/2) > APPLICATION_WIDTH-PADDLE_WIDTH) {
                PADDLE.setLocation(APPLICATION_WIDTH-PADDLE_WIDTH, HEIGHT-PADDLE_Y_OFFSET);
            }
        }
    }

    /** called on mouse click to start the game */
    private void gameStart() {
        int counter = 100; /* number of blocks, subtracts everytime a block is removed */
        while(true) {
            moveBall();
            if(checkCollision()) {
                counter --; /* a brick is gone */
                checkCounter(counter); /* checks to see if the ball should speed up */
            }
            pause(DELAY); /* add a delay so the ball isn't too fast */
            if(counter == 0) {
                YOUWIN.setLocation(APPLICATION_WIDTH/2 - YOUWIN.getWidth()/2, APPLICATION_HEIGHT/2 - YOUWIN.getHeight()/2);
                YOUWIN.setFont(new Font("Arial",Font.BOLD,18));
                add(YOUWIN);
                break;
            }
        }
    }

    private void checkCounter(int counter) {
        if(counter % 10 == 0) {
            changeSpeeds();
        }
    }

    private boolean checkCollision() {
        GObject collider = getCollidingObject();
        if(collider != null) {
            if(collider == PADDLE) {
                Y_VELOC = -Y_VELOC; /* move the opposite way */
                BALL.move(X_VELOC, Y_VELOC);
                return false;
            } else {
                remove(collider);
                Y_VELOC = -Y_VELOC;
                /* only returns true if the ball hits a block */
                return true;
            }
        }
        return false;
    }

    private void makeBall() {
        BALL.setLocation(APPLICATION_WIDTH/2-BALL_RADIUS, APPLICATION_HEIGHT/2-BALL_RADIUS);
        BALL.setFilled(true);
        add(BALL);
    }

    private void changeSpeeds() {
        if(Y_VELOC < 0) {
            Y_VELOC -= .25; /* if it's negative, speed it up by subtracting */
        } else {
            Y_VELOC += .25; /* if positive, speed it up by adding */
        }
        X_VELOC = setRandom(); /* change the x speed */
        GETTINGFASTER.setFont(new Font("Arial",Font.BOLD,18));
        add(GETTINGFASTER);
    }

    private void moveBall() {
        BALL.move(X_VELOC, Y_VELOC); /* ball moves according to the set velocities */
        if(BALL.getY() < 0) {
            Y_VELOC = -Y_VELOC;
        }
        if(BALL.getY() > APPLICATION_HEIGHT) {
            BALL.setLocation(BALL.getX(), BALL.getY()); /* means you lost */
            YOULOSE.setLocation(APPLICATION_WIDTH/2 - YOUWIN.getWidth()/2, APPLICATION_HEIGHT/2 - YOUWIN.getHeight()/2);
            YOULOSE.setFont(new Font("Arial",Font.BOLD, 18));
            add(YOULOSE);
        }
        if(BALL.getX() > APPLICATION_WIDTH-PADDLE_WIDTH/2 || BALL.getX() < 0) {
            X_VELOC = -X_VELOC;
            BALL.move(X_VELOC, Y_VELOC); /* does this fix the side walls */
        }
    }

    private GObject getCollidingObject() {
        if (getElementAt(BALL.getX(), BALL.getY()) != null) {
            /* top left corner */
            return getElementAt(BALL.getX(), BALL.getY()); 

        } else if (getElementAt(BALL.getX() + 2*BALL_RADIUS, BALL.getY()) != null) {
            /* top right corner */
            return getElementAt(BALL.getX() + 2*BALL_RADIUS, BALL.getY());

        } else if (getElementAt(BALL.getX(), BALL.getY() + 2*BALL_RADIUS) != null) {
            /* bottom left corner */
            return getElementAt(BALL.getX(), BALL.getY() + 2*BALL_RADIUS);

        } else if (getElementAt(BALL.getX() + 2*BALL_RADIUS, BALL.getY() + 2*BALL_RADIUS) != null) {
            /* bottom right corner */
            return getElementAt(BALL.getX() + 2*BALL_RADIUS, BALL.getY() + 2*BALL_RADIUS);

        } else {
            return null;
        }
    }

    private double setRandom() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        X_VELOC = rgen.nextDouble(1.0, 3.0);
        if(rgen.nextBoolean(0.5)) X_VELOC = -X_VELOC; /* makes vx negative half the time */
        return X_VELOC;
    }

    /* private instance variables */

    /* objects */
    public GRect PADDLE = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT); /* paddle */
    public GOval BALL = new GOval(2*BALL_RADIUS, 2*BALL_RADIUS); /* ball */

    /* labels */
    public GLabel YOULOSE = new GLabel("You Lose!", WIDTH/2 - (PADDLE_WIDTH/2), HEIGHT/2);
    public GLabel YOUWIN = new GLabel("You Win!", WIDTH/2 - (PADDLE_WIDTH/2), HEIGHT/2);
    public GLabel GETTINGFASTER = new GLabel("It's getting faster!", APPLICATION_WIDTH/2 - YOUWIN.getWidth()/2, APPLICATION_HEIGHT/2 - YOUWIN.getHeight()/2);

    /* audio clip to play */
    public AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");

    /* controls the velocity of the ball */
    private double X_VELOC, Y_VELOC;
}
