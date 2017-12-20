function Player(x, y) {
    this.w = 15;
    this.h = 100;
    this.x = x;
    this.y = y;
    this.moveUp = 15;
    this.moveDown = 15;
    
    this.drawRect = function() {
        rectMode(CENTER);
        fill(255);
        rect(this.x, this.y, this.w, this.h);
    }
    
    this.move = function(d) {
        this.d = d;
        this.y += this.d;
    }
    
}