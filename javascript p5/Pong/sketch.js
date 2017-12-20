var ball;
var player1, player2;
function setup() {
    createCanvas(600, 400);
    ball = new Ball();
    player1 = new Player(10, height/2);
    player2 = new Player(width-20, height/2);

}

function draw() {
    background(0);
    
    ball.update();
    ball.show();
    
    player1.drawRect();
    player2.drawRect();
}

function keyPressed() {
    if (keyCode === 87) {
        player1.move(-15);
    }
    else if (keyCode === 83) {
        player1.move(15);
    }
    
    if (keyCode === UP_ARROW) {
        player2.move(-15);
    }
    else if (keyCode === DOWN_ARROW) {
        player2.move(15);
    }
    
}