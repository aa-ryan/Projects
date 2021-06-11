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
    print!("Difficulty: ");
    io::stdout().flush();
    io::stdin().read_line(&mut difficulty);

    let diff = difficulty.trim().parse::<u32>().expect("We need an integer");
    println!("Generating Genesis Block!");
    let chain = &mut chain::Chain::new(miner_addr
                                .trim()
                                .to_string(), 
                                diff);

    loop {
        println!("Menu");
        println!("1) New Transaction\n2) Mine Block\n3) Change Difficulty\n4) Change Reward\n5)Exit");
        println!("Enter your choice: ");
        io::stdout().flush();
        choice.clear();
        io::stdin().read_line(&mut choice);
        println!("");

        match choice.trim().parse().unwrap() {
            5 => 
            {
                println!("Exiting");
                process::exit(0);
            },
            1 => {new_t(chain)},
            2 => {gen_b(chain)},
            3 => {diff_c(chain)},
            4 => {reward_c(chain)},
            _ => println!("Invalid option please retry"),
        }
    }
}


fn new_t(chain: &mut chain::Chain) {
    let mut sender = String::new();
    let mut receiver = String::new();
    let mut amount = String::new();
    print!("Enter sender address: ");
    io::stdout().flush();
    io::stdin().read_line(&mut sender);
    print!("Enter the receiver address: ");
    io::stdout().flush();
    io::stdin().read_line(&mut receiver);
    print!("Enter amount: ");
    io::stdout().flush();
    io::stdin().read_line(&mut amount);

    let res = chain.new_transaction(sender.trim().to_string(),
                                    receiver.trim().to_string(),
                                    amount.trim().parse().unwrap());
    
    match res {
        true => println!("Transaction Added"),
        false => println!("Transaction Failed")
    }

}

fn gen_b(chain: &mut chain::Chain) {
    println!("Generating Block");
    let res = chain.generate_new_block();
    match res {
        true => println!("Block Generated Successfully"),
        false => println!("Block Generatiion Failed"),
    }
}

fn diff_c(chain: &mut chain::Chain) {
    let mut new_diff = String::new();
    print!("Enter new Difficulty");
    io::stdout().flush();
    io::stdin().read_line(&mut new_diff);
    let res = chain.update_difficulty(new_diff.trim().parse().unwrap());
    match res {
        true => println!("Updated Difficulty"),
        false => println!("Failed to update difficulty")
    }

}

fn reward_c(chain: &mut chain::Chain) {
    let mut new_reward = String::new();
    print!("Enter new reward: ");
    io::stdout().flush();
    io::stdin().read_line(&mut new_reward);
    let res = chain.update_reward(new_reward.trim().parse().unwrap());
    match res {
        true => println!("Updated Reward"),
        false => println!("Failed to update reward")
    }

}
