import os
import subprocess
import sys

LABELS = ['FIRST', 'SECOND']  # labels for players for easier output

# assuming we have to get at least one word in word group, check if we have enough args
if len(sys.argv) < 5:
    sys.stderr.write('Not enough arguments');
    sys.exit(1)
playersPath = [sys.argv[1], sys.argv[2]]  # list of players' path
# check if paths we got are valid
if not (os.path.exists(playersPath[0]) or os.path.exists(playersPath[1])):
    sys.stderr.write("Can't find a file in the given path")
    sys.exit(2)
lastWord = sys.argv[3].lower()  # last used word
wordGroupCmd = sys.argv[4:]  # word group list for command input
wordGroupCmd = [item.lower() for item in wordGroupCmd]  # lower case

wordGroup = {}
for i in range(3, len(sys.argv)):  # iterate through words in set
    firstChar = sys.argv[i][0].lower()  # get the first char
    if firstChar in wordGroup:  # check if list for first char already exists
        wordGroup[firstChar].append(sys.argv[i].lower())
    else:
        wordGroup[firstChar] = [sys.argv[i].lower()]
    
currentPlayer = 0  # current player, 0 for first, 1 for second
gameRunning = True  # boolean to check if the game should continue or not
judgment = 'OK'  # string for valid/invalid output
while (gameRunning):
    # launch an AI  
    cmdList = ['C:/Program Files/Git/usr/bin/bash.exe', playersPath[currentPlayer], lastWord] + wordGroupCmd
    try:
        result = subprocess.run(cmdList, stdout=subprocess.PIPE)
        answer = result.stdout
    except:
        sys.stderr.write('Error occured during AI launch')
        sys.exit(3)
    if answer == None:  # set an answer to empty if none were received
        answer = ''
    answer = answer.decode()  # decode answer from bytes to string
    answer = answer.replace("\n", "")  # replace any linebreaks and newlines
    answer = answer.replace("\r", "")
    if answer == '':
        gameRunning = False
    else:
        firstChar = answer[0]        
        if (lastWord.endswith(firstChar)):  # check if first char of answer is the same as the last char of the last word
            if firstChar in wordGroup:  # check if list for that character exists
                if answer in wordGroup[firstChar]:  # check if an answer is in the word group
                    wordGroup[firstChar].remove(answer)  # if everything's ok - remove the word from set
                    wordGroupCmd.remove(answer)
                else:
                    gameRunning = False
            else:
                gameRunning = False
        else:
            gameRunning = False
        lastWord = answer
    if not gameRunning:  # change string if answer was not valid
        judgment = 'NG'
    print('{0} ({1}): {2}'.format(LABELS[currentPlayer], judgment, answer))  # print out an answer
    currentPlayer = 1 - currentPlayer  # change current player
print('WIN - {0}'.format(LABELS[currentPlayer]))
sys.exit(0)
