I don't really know what I was supposed to answer in 2A-3: about this project or about all the projects I worked with.
So I'll answer both.

1) This project
First of all, code which is submitted at codecheck is not final. My final code will be on the github: https://github.com/mgkrp/codecheck-18881/ . It is also linked on the task page.
The reason why I have submitted not final code is because I have a habit of always pressing Ctrl+S after I write any code, and codecheck actually saves it. But after you save code and close the editor, it is submitted. I thought "Ok, I can just not close the editor while I work on it", but then my laptop turned off. So the version submitted was still in progress.
Anyway, I've both 2A-1 and 2A-2 in two languages: Java and Python. Framework logic is the same in both languages. AI logic is different in Java and Python.
In Java, AI simply iterates through the word in the word group and picks the first one valid.
In Python, AI plays its best. It uses depth-first search to look through all the game variants and looks for the guaranteed win. If there is no guaranteed win available, it will choose the word with the most percentage of winning variants.
Also, since I wasn't able to test my program at codecheck, I had to do it on my local machine which runs on Windows, so some parts are different from what shoult it be on UNIX-like system, since you need to manually point to bash.exe to launch any shell scripts on windows.

2) All the projects
 - Window manager for sensor display. It was developed for my bachelor's degree, for russian military UNIX-like OS (Astra Linux), using Perl and a little of bash. As a base for manager was used open-source manager fvwm, which was later configured to my needs. Can't really show it, since I wasn't allowed to take product code outside of building.
 - Worked as a part of science group during my first year at university, focused on programming swarm robots (coin-sized machines which are supposed to work together). I was working on path finding with obstacles and path of area coverage. There is a publication in a science journal, but it's in russian only.
 - Half-social, half-programming paper about social trend happening in the russian social networks tied to teenagers suicide. Programming part was about classifing communities as "good" or "bad", based on results of Bayesian machine learning. Code can be found at https://github.com/mgkrp/seaofwhales , but it doesn't really work right now, since data from different communities, which are now banned/closed.
 - Not really finished project, but currently working fullstack on IoT web-platform, django for backend and vue.js for frontend.
 - Bunch of pet-projects I did as a part of university's assignments or just for myself. Those include: digit recognition neural network, chatbot for Telegram, simulator for game's ladder rankings.