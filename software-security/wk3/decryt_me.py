from collections import Counter
import re

ENCRYPTED_MESSAGE='BL PLR IREELIW DMCP XMDI LA RCWVDWC ALLB OVP XW ZVUUWB VUMOWCDVYP EVYDMZUWI?'
FILE_NAME='words.txt'
LONGEST_WORD_LEN=len(max(open(FILE_NAME,'r'),key=len))

wordsOfLen={}

for index in range(LONGEST_WORD_LEN):
    print(index+1)
    # create list of words for all lenghs up to max len
    #print(re.findall(r'\w{%d}' % (index+1), open(FILE_NAME,'r')))

    with open(FILE_NAME) as wordfile:
        for line in wordfile:
            # assign numbers to their corresponding length
            if wordsOfLen != exist
                wordsOfLen = []
            wordsOfLen[len(line)].append(line)
            #line=re.findall(r'\wL',line)
            if len(line)==(index+1):
                print(line)

for word in ENCRYPTED_MESSAGE.split(' '):
    print(word)

#wordsfile=open(FILE_NAME,'r')
#words=wordsfile.read().split('\n')
#words.sort()
#words.sort(key=len,reverse=True)

print(ENCRYPTED_MESSAGE)
print(Counter(ENCRYPTED_MESSAGE))

#print(words)
#sexbomb=filter(lambda word: len(word) == 2, words)
#for w in sexbomb:
    #print(w)

# swap all L for O
# try all len(first word) which match the defined letters e.g. *O* 
# use this switch to match all subsequent words
# see if this matches any other words in the words.txt file
# if no words match the word.txt file with the defined letters in that positon, return false and move onto next word to substitute for

