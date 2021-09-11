from collections import deque
import random
import re
import time

from colorama import init, Fore, Back, Style

# Colorama init
init()

def help():
    """Print help message"""
    s = """
        UNO help:
        ("D -> Draw, P -> Pass, S -> Stats, Q -> Quit\n")
        ("Add R, G, B or Y to WILD cards\n")
        ("Type UNO at end of card you're playing to say UNO\n")
    """
    return s

class Player():
    """This class manages the players name, hand, drawing and playing cards."""

    def __init__(self, name="Player", brain="CPU"):
        # Default constructor
        """Player init"""
        self.name = name
        self.hand = []
        self.brain = brain
        self.said_uno = False

    def num_cards(self):
        """Return number of cards in Player's hand"""
        return len(self.hand)

    def draw_cards(self, card):
        """Appends (draws) a card to players hand"""
        self.hand.append(card)

    def play_card(self, card):
        """Removes (plays) the card from the players hand"""
        # We only need 4 chars, hence 0:4 slicing
        try:
            c = self.hand.pop(self.hand.index(card[0:4]))
            return True
        except:
            return False

    def show_hand(self):
        """Return the players hand, in color"""
        h = ""
        for card in self.hand:
            h += "||" + colorize_card(card) + "||"

        return h

    def score_hand(self):
        """Return the score of the players hand"""
        score = 0
        for c in self.hand:
            # number of cards are face value
            if re.search("/d", c[1:2]):  # re.search(pattern, string, flags=0)
                score += int(c[1:2])

            if re.search("[R|S|-]", c[1:2]) and c[0:1] != "W":
                score += 20

            if re.search("^W", c):
                score += 50

        return score

def colorize_card(card):
    """
    Returns the card with special color formatting for printing to the screen
    """

    colored_card = card
    if card[0:1] == "R" or card[4:5] == "R":
        colored_card = Back.RED + Fore.WHITE + card + Fore.RESET + Back.RESET

    if card[0:1] == "G" or card[4:5] == "G":
        colored_card = Back.GREEN + Fore.BLACK + card + Fore.RESET + Back.RESET

    if card[0:1] == "B" or card[4:5] == "B":
        colored_card = Back.BLUE + Fore.WHITE + card + Fore.RESET + Back.RESET

    if card[0:1] == "Y" or card[4:5] == "Y":
        colored_card = Back.YELLOW + Fore.YELLOW + card + Fore.RESET + Back.RESET

    if card[0:1] == "W" and len(card) == 4:
        colored_card = Back.RED + Fore.WHITE + card[0:1] \
            + Back.GREEN + Fore.BLACK + card[1:2] \
            + Back.BLUE + Fore.WHITE + card[2:3] \
            + Back.YELLOW + Fore.BLACK + card[3:4] \
            + Fore.RESET + Back.RESET

    return colored_card


