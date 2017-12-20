function ScoreBoard() {
    this.x= 60;
    this.y = 35;
    
    this.show = function() {
        noFill();
        strokeWeight(10);
        stroke(255);
        rectMode(CENTER);
        rect(this.x, this.y, 100, 50);
    }
}