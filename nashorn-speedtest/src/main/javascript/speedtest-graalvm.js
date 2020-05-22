const esprima = require('./esprima');
const fs = require("fs");

const readFile = (fname) => fs.readFileSync(fname, 'utf-8');
const timestamp = () => Math.round(process.hrtime()[1] / 1e6);

function parse(code) {
    for (let i = 1; i <= 30; ++i) {
        const start = timestamp();
        const tree = esprima.parse(code);
        const tokens = esprima.tokenize(code);
        const stop = timestamp();
        if (i < 3 || i%10===0) {
            console.log('Run #' + i + ':', (stop - start), 'ms');
        }
    }
}

() => parse(readFile('./jquery-3.5.1.min.js'));
