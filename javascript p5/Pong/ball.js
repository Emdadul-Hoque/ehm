function Ball() {
    this.x = width/2;
    this.y = height/2;
    this.w = 20;
    this.h = 20;
    this.Xspeed = 1;
    this.Yspeed = 1;
    
    this.show = function() {
        fill(255);
        ellipse(this.x, this.y, this.w, this.h);
    }
    
    this.reset = function() {
        this.x = width/2;
        this.y = height/2;
        
        this.Xspeed = 3;
        this.Yspeed = 3;
    }
    
    this.update = function() {
        this.x += this.Xspeed;
        this.y += this.Yspeed;
        
        if (this.y > height || this.y < 0) {
            this.Yspeed *= (-1);
        }
        
        if (this.x > width || this.x < 0) {
            this.reset();
        }
        
        if ((this.x + 10 >= player2.x - 5) || (this.x - 10 <= player1.x +5)) {
            if (((this.y +10 <= player1.y + 50) && (this.y - 10 >= player1.y - 50))
                || ((this.y + 10 <= player2.y + 50) && (this.y - 10 >= player2.y - 50)))
            this.Xspeed *= (-1);
        }
    }
}