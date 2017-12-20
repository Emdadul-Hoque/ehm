function Face(boolvalue) {
    this.xcor = random(15, width - 15);
    this.ycor = 15;
    this.itemClicker = boolvalue;
    
    this.show = function() {
        if(this.itemClicker == 0) {
            fill(random(255), random(255), random(255));
            ellipse(this.xcor, this.ycor, 20, 20);
        } else if(this.itemClicker == 1) {
            fill(random(255), random(255), random(255));
            rect(this.xcor, this.ycor, 20, 20);
        }
    }
    
    this.update = function() {
        this.xcor += random(-15, 15);
        this.ycor += random(2);
        if(this.xcor < 10) {
            this.xcor = 10 + (10 - this.xcor);
        } else if(this.xcor > width - 10) {
            this.xcor = (width - 10) - (this.xcor - (width - 10));
        }
    }
    
    this.popOut = function() {
        if(this.ycor > height - 10) {
            return 1;
        } else {
            return 0;
        }
    }
    
    this.returnX = function() {
        return this.xcor;
    }
    
    this.returnY = function() {
        return this.ycor;
    }
}
