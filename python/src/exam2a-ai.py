import sys
import copy

# function with depthsearching for the best option available
# takes words - dictionary with available words, firstChar - character to start, playersTurn - boolean which marks which turn is it 
def getGamesResult(words, firstChar, playersTurn):
    losingGames = 0.0  
    winningGames = 0.0
    maxPercent = 0.0  # best win chance for this situation
    bestWord = ""
    if (firstChar not in words) or (len(words[firstChar]) == 0):  # if no available legit moves
        if playersTurn:  # if our turn - 
            losingGames += 1  # we lost
        else:  # if not ours turn -
            winningGames += 1  # we won
    else:  # if moves are available
        for string in words[firstChar]:  # iterate through all the moves
            # create a copy of a dict and remove current string from it
            tempWords = copy.deepcopy(words) 
            tempWords[firstChar].remove(string)
            # call getGamesResult without current string and change a player
            # receives best string, number of lost games and number of won games          
            foo, curLosingGames, curWinningGames = getGamesResult(tempWords, string[-1], not playersTurn)
            if maxPercent <= curWinningGames/(curWinningGames+curLosingGames):  # if win percent is higher than curren max
                maxPercent = curWinningGames/(curWinningGames+curLosingGames)  # change current max and current best word
                bestWord = string
            losingGames += curLosingGames  
            winningGames += curWinningGames

    return bestWord, losingGames, winningGames

# assuming we have to get at least one word in word group, check if we have enough args
if len(sys.argv) < 2:
    sys.err.write("Not enough args\n")
    sys.exit(1)
lastWord = sys.argv[1]
wordGroup = {}
for i in range(2, len(sys.argv)):  # iterate through words in set
    firstChar = sys.argv[i][0].lower()  # get the first char
    if firstChar in wordGroup:  # check if list for first char already exists
        wordGroup[firstChar].append(sys.argv[i].lower())
    else:
        wordGroup[firstChar] = [sys.argv[i].lower()]

answer, losingGames, winningGames = getGamesResult(wordGroup, lastWord[-1], True)  # get an answer to print
print(answer)
sys.exit(0)
