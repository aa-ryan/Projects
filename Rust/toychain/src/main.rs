#[macro_use]
extern crate serde_derive;

use std::io;
use std::process;
use std::io::Write;

mod chain;

fn main() {
    let mut miner_addr = String::new();
    let mut difficulty = String::new();
    let mut choice = String::new();

    print!("Input Miner Address: ");
    io::stdout().flush();
    io::stdin().read_line(&mut miner_addr);
    print!("Difficulty: ");;
    io::stdout().flush();
    io::stdin().read_line(&mut difficulty);

    let diff = difficulty.trim().parse::<u32>().expect("We need an integer");
    println!("Generating Genesis Block!");
    let mut chain = chain::Chain::new(miner_addr
                                .trim()
                                .to_string(), 
                                diff);
}
