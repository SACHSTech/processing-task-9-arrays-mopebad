import processing.core.PApplet;

public class Sketch extends PApplet {

  float[] floatSnowY = new float[15];
  float[] floatSnowX = new float[15];
  boolean[] snowFlakeHide = new boolean[15];
  float snowSpeed = 1.5f; // Speed of snowfall

  // Variables for player circle
  int intCircleX;
  int intCircleY;
  int intLives = 3; // Lives of the player
  boolean playerHit = false;

  // Variables to manage sun movement
  boolean moveUp = false;
  boolean moveDown = false;
  boolean moveRight = false;
  boolean moveLeft = false;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    // Size call
    size(700, 650);
  }

  /**
   * Called once at the beginning of execution. Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(0);
    // Initialize snowflake x and y positions randomly
    for (int i = 0; i < floatSnowX.length; i++) {
      floatSnowX[i] = random(width);
      floatSnowY[i] = random(-500, 0); // Start snowflakes off-screen
      snowFlakeHide[i] = false;
    }
    intCircleX = width / 2;
    intCircleY = height - 50;
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    if (intLives > 0) {
      background(0);

      // Display lives indicator (squares)
      fill(255);
      for (int i = 0; i < intLives; i++) {
        rect(width - 40, 20 + i * 20, 15, 15);
      }

      // Update and display snowflakes
      for (int i = 0; i < floatSnowY.length; i++) {
        if (!snowFlakeHide[i]) {
          fill(255);
          ellipse(floatSnowX[i], floatSnowY[i], 30, 30); // Draw snowflake
          floatSnowY[i] += snowSpeed; // Move snowflake down

          // Reset snowflake to top when it reaches bottom
          if (floatSnowY[i] > height) {
            floatSnowY[i] = 0;
            floatSnowX[i] = random(width);
          }

          // Check collision with player
          if (dist(intCircleX, intCircleY, floatSnowX[i], floatSnowY[i]) < 15) {
            snowFlakeHide[i] = true; // Hide snowflake
            intLives--; // Decrease lives
            if (intLives <= 0) {
              background(255); // Clear screen to white when lives run out
            }
          }
        }
      }

      if (intLives > 0) {
        // Existing code for drawing the game elements

        // Draw and update player circle
        fill(0, 0, 255);
        ellipse(intCircleX, intCircleY, 35, 35);
        
        if (moveRight == true) {

        }
        }
      } else {
        // If player's lives run out, clear the screen to white
        background(255);
      }
    }
  }

   public void keyPressed() {
    if (keyPressed) {

      if (key == 'd' || key =='D') {

        moveRight = true;

      } if (key == 'a' || key == 'A') {

        moveLeft = true;

      } if (keyCode == LEFT) {

        moveLeft = true;

      } if (keyCode == RIGHT) {

        moveRight = true;

      }
    }
  }

  public void keyReleased() {
 
    if (key == 'd' || key =='D') {

      moveRight = false;

    } if (key == 'a' || key == 'A') {

      moveLeft = false;

    } if (keyCode == LEFT) {

      moveLeft = false;

    } if (keyCode == RIGHT) {

      moveRight = false;
      
    }
  }


  

  // Mouse click to hide snowflakes
  public void mouseClicked() {
    for (int i = 0; i < floatSnowY.length; i++) {
      if (!snowFlakeHide[i] && dist(floatSnowX[i], floatSnowY[i], mouseX, mouseY) < 15) {
        snowFlakeHide[i] = true;
      }
    }
  }
}
