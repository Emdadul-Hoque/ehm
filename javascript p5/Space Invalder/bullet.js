function Bullet(x, y) {
    this.x = x;
    this.y = y;
    this.r = 7.5;
    this.score = 0;
    
    this.trig = function() {
        fill(0, 0, 255);
        ellipse(this.x, this.y, this.r * 2, this.r * 2);
    }
    
    this.move = function() {
        this.y -= 5;
    }
    
    this.hits = function(a, b) {
        if(dist(this.x, this.y, a, b) < this.r + 10) {
            return true;
        }
        else {
           return false;
        }
    }
}
