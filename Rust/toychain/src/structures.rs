
#[derive(Debug, Clone, Serialize)]
struct Transaction {
    sender: String,
    receiver: String,
    amount: f32,
}

#[derive(Serialize , Debug)]
pub struct Blockheader {
    timestamp: i64,
    nonce: u32,
    preHash: String,
    merkle: String,
    difficulty: u32,
}


#[derive(Serialize, Debug)]
pub struct Block {
    header: Blockheader,
    count: u32,
    transaction: Vec<Transaction>
}

pub struct Chain {
    chain: Vec<Block>,
    currentTransaction: Vec<Transaction>,
    difficulty: u32,
    minerAddress: String,
    reward: f32
}
