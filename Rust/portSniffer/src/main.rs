use std::env;
use std::process;
use std::sync::mpsc::channel;
use std::thread;

mod scan;
mod arguments;

fn main() {
    let args: Vec<String> = env::args().collect();
    let program = args[0].clone();
    let arguments = arguments::Arguments::new(&args).unwrap_or_else(
        |err| {
            if err.contains("help") {
                process::exit(0);
            } else {
                eprintln!("{} problem parsing arguments: {}", program, err);
                process::exit(0);
            }
        }
    );

    let num_threads = arguments.threads;
    let addr = arguments.ipaddr;
    let (tx, rx) = channel();
    for i in 0..num_threads {
        let tx = tx.clone();

        thread::spawn(move || {
            scan::scanp(tx, i, addr, num_threads);
        });
    }

    let mut out = vec![];
    drop(tx);  // tx in other thread not in main thread, scope over
    for p in rx {
        out.push(p);
    }    

    println!("");
    out.sort();
    for v in out {
        println!("PORT: {} is open", v);
    }
}

