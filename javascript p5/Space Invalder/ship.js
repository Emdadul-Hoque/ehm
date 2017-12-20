function Ship() {
    this.x = width/2;
    this.y = height;
    this.xdir = 0;
    
    this.art = function() {
        fill(255, 0, 255);
        noStroke();
        rectMode(CENTER);
        rect(this.x, this.y - 15, 70, 30);
        ellipse(this.x, this.y - 30, 50, 50);
        rect(this.x, this.y - 55, 15, 30);
    }
    
    this.setDir = function(dir) {
        this.xdir = dir;
    }
    
    this.move = function() {
        if(this.x >= 0 && this.x <= width) {
            this.x += this.xdir * 5;
        }
        if(this.x < 0) {
            this.x += 0.5;
        } else if(this.x > width) {
            this.x -= 0.5;
        }
    }
}
