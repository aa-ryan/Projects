extern crate time;
extern crate serde;
extern crate serde_json;
extern crate sha2;

use self::sha2::{Sha256, Digest};
use std::fmt::Write;

mod structures;

impl Chain {

    pub fn new(minerAddress: String, difficulty: u32) -> Chain {
        let mut chain = Chain {
            chain: Vec::new(),
            currentTransaction: Vec::new(),
            difficulty,
            minerAddress,
            reward: 100.0,
        };

        chain.generate_new_block();
        chain
    }

}
