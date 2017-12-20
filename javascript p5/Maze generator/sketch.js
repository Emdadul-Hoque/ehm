var cols, rows;
var w = 20;
var grid = [];
var end, current, cnt = 0, currentSolve;
var stack = [];
var solveStack = [];

function setup() {
    createCanvas(400, 400);
    cols = width / w;
    rows = height / w;
    
    for(var j = 0; j < rows; j++) {
        for(var i = 0; i < cols; i++) {
            var cell = new Cell(i, j);
            grid.push(cell);
        }
    }
    
    end = grid[(cols * rows) - 1];
    current = grid[0];
    currentSolve = grid[0];
}

function draw() {
    background(0);
    for(var i = 0; i < grid.length; i++) {
        grid[i].show();
    }
    current.visited = true;
    currentSolve.solvingVisit = true;
    var next = current.checkNeighbours();
    if(next) {
        next.visited = true;
        stack.push(current);
        removeWalls(current, next);
        current = next;
        cnt++;
    } else if(stack.length > 0) {
        current = stack.pop();
    }
    if(cnt + 1 == cols * rows && currentSolve != end) {
        //frameRate(2);
        var nxt = currentSolve.nextPoint();
        if(nxt) {
            //if(checkWalls(currentSolve, nxt)) {
                solveStack.push(currentSolve);
                nxt.solvingVisit = true;
                currentSolve = nxt;
            //}
        } else if(solveStack.length > 0) {
            currentSolve = solveStack.pop();
            currentSolve.unsuccessfulVisit = true;
        }
    }
}

function index(i, j) {
    if(i < 0 || j < 0 || i > cols - 1 || j > rows - 1) {
        return -1;
    }
    return i + j * cols;
}

function Cell(i, j) {
    this.i = i;
    this.j = j;
    this.walls = [true, true, true, true];
    this.visited = false;
    this.solvingVisit = false;
    this.unsuccessfulVisit = false;
    
    this.checkWalls = function(b) {
        var x = this.i - b.i;
        var y = this.j - b.j;
        if(x == 1) {
            if(this.walls[3] == false && b.walls[1] == false) {
                return true;
            }
        } else if(x == -1) {
            if(this.walls[1] == false && b.walls[3] == false) {
                return true;
            }
        }
        if(y == 1) {
            if(this.walls[0] == false && b.walls[2] == false) {
                return true;
            }
        } else if(y == -1) {
            if(this.walls[2] == false && b.walls[0] == false) {
                return true;
            }
        }
        return false;
    }
    
    this.nextPoint = function() {
        var neighbours = [];
        
        var top = grid[index(i, j - 1)];
        var right = grid[index(i + 1, j)];
        var bottom = grid[index(i, j + 1)];
        var left = grid[index(i - 1, j)];
        
        if(top && (!top.solvingVisit || !top.unsuccessfulVisit) && this.checkWalls(top)) {
            neighbours.push(top);
        }
        if(right && (!right.solvingVisit || !right.unsuccessfulVisit) && this.checkWalls(right)) {
            neighbours.push(right);
        }
        if(bottom && (!bottom.solvingVisit || !bottom.unsuccessfulVisit) && this.checkWalls(bottom)) {
            neighbours.push(bottom);
        }
        if(left && (!left.solvingVisit || !left.unsuccessfulVisit) && this.checkWalls(left)) {
            neighbours.push(left);
        }
        
        if(neighbours.length > 0) {
            var r = floor(random(0, neighbours.length));
            return neighbours[r];
        } else {
            return undefined;
        }
    }
    
    this.checkNeighbours = function() {
        var neighbours = [];
        
        var top = grid[index(i, j - 1)];
        var right = grid[index(i + 1, j)];
        var bottom = grid[index(i, j + 1)];
        var left = grid[index(i - 1, j)];
        
        if(top && !top.visited) {
            neighbours.push(top);
        }
        if(right && !right.visited) {
            neighbours.push(right);
        }
        if(bottom && !bottom.visited) {
            neighbours.push(bottom);
        }
        if(left && !left.visited) {
            neighbours.push(left);
        }
        
        if(neighbours.length > 0) {
            var r = floor(random(0, neighbours.length));
            return neighbours[r];
        } else {
            return undefined;
        }
    }
    
    this.show = function() {
        var x = this.i * w;
        var y = this.j * w;
        stroke(255);
        if(this.walls[0]) {
            line(x, y, x + w, y);
        }
        if(this.walls[1]) {
            line(x + w, y, x + w, y + w);
        }
        if(this.walls[2]) {
            line(x + w, y + w, x, y + w);
        }
        if(this.walls[3]) {
            line(x, y + w, x, y);    
        }
        
        if(this.visited) {
            noStroke();
            fill(255, 0, 255, 100);
            rect(this.i * w, this.j * w, w, w);
        }
        
        if(this.solvingVisit) {
            noStroke();
            fill(255, 0, 0, 100);
            rect(this.i * w, this.j * w, w, w);
        }
        
        if(this.unsuccessfulVisit) {
            noStroke();
            fill(134, 126, 126, 100);
            rect(this.i * w, this.j * w, w, w);
        }
    }    
}

function removeWalls(a, b) {
    var x = a.i - b.i;
    if(x === 1) {
        a.walls[3] = false;
        b.walls[1] = false;
    } else if(x === -1) {
        a.walls[1] = false;
        b.walls[3] = false;
    }
    
    var y = a.j - b.j;
    if(y === 1) {
        a.walls[0] = false;
        b.walls[2] = false;
    } else if(y === -1) {
        a.walls[2] = false;
        b.walls[0] = false;
    }
}
/*
function checkWalls(a, b) {
    var x = a.i - b.i;
    var y = a.j - b.j;
    if(x == 1) {
        if(a.walls[3] == false && b.walls[1] == false) {
            console.log(b.walls);
            return true;
        }
    } else if(x == -1) {
        if(a.walls[1] == false && b.walls[3] == false) {
            console.log(b.walls);
            return true;
        }
    }
    if(y == 1) {
        if(a.walls[0] == false && b.walls[2] == false) {
            console.log(b.walls);
            return true;
        }
    } else if(y == -1) {
        if(a.walls[2] == false && b.walls[0] == false) {
            console.log(b.walls);
            return true;
        }
    }
    return false;
}
*/