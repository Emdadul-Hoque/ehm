var ship;
var bullet = [];
var face = [];
var score = 0;
var board;

function setup() {
    createCanvas(600, 400);
    ship = new Ship();
    board = new ScoreBoard();
}

function draw() {
    background(51);
    textSize(32);
    fill(0, 250, 152);
    text(score, 50, 45);
    //board.show();
    for(var i=0; i < bullet.length; i++) {
        bullet[i].trig();
        bullet[i].move();
        if(bullet[i].y < 7.5) {
            bullet.splice(i, 1);
        }
    }
    ship.art();
    ship.move();
    if(frameCount % 25 == 0) {
        face.push(new Face(0));
    } else if(frameCount % 29 == 0) {
        face.push(new Face(1));
    }
    for(var i = 0; i < face.length; i++) {
        face[i].show();
        face[i].update();
        var p = face[i].popOut();
        var x = face[i].returnX();
        var y = face[i].returnY();
        if(p == 1){
            face.splice(i, 1);
        } else {
            for(var j = 0; j < bullet.length; j++){
                if(bullet[j].hits(x, y)) {
                    face.splice(i, 1);
                    bullet.splice(j, 1);
                    score++;
                    console.log(score);
                }
            }
        }
    }
}

function keyReleased() {
    if(key != ' ') {
        ship.setDir(0);
    }
}

function keyPressed() {
    if(key === ' ') {
        bullet.push(new Bullet(ship.x, ship.y - 70));
    }
    
    if(keyCode === 37) {
        ship.setDir(-1);
    } else if(keyCode === 39) {
        ship.setDir(1);
    }
}
