# What is STEMSpace?
STEMSpace hopes to create interactive labs for anybody (although students are the main target) to understand, explore, and experiment with the worlds of Science, Technology, and Mathematics. I hope that it will be built efficiently first for MacOS with Xcode and for Android with Android Studio, then maybe extending to other platforms.

## Basic Organization
STEMSpace will be split into areas, that lead to their own labs or "spaces". For an overview, you can click [here](https://coggle.it/diagram/YDJtVy31dzpo0GU4/t/stemspace-frame-layout/ede6290155445b981a58907b730c278a6a8ad11c5b30106bd9e99eeb650f94b0). This layout is bound to change and/or expand at any time, but feel free to make reccomendations for possible labs or "spaces".

1. SciSpace
    - BioSpace
        - ALife Explorer
            - Cellular Automata Engine
            - Agent System Engine
        - LifeTree Explorer
            - Animal Explorer
    - ChemSpace
        - Periodic Table Explorer
        - Atom Builder
        - Molecule Builder
        - Reactions Lab
2. TechSpace
    - Aerodynamics Lab
    - Circuitry Lab
    - Cybersecurity Explorer
        - Encryption
        - Steganography
3. MathSpace
    - Randomness
    - Sequences + Series
    - Geometry

## Tasks
- [x] Make README
- [x] Make Gitignore
- [x] Plan Organization
- [ ] Build Python Project
    - [ ] Home Screen
    - [ ] SciSpace
        - [ ] ChemSpace
            - [ ] Periodic Table
                - [ ] First-Time Entrance Pop-Up
                - [ ] Create class "table" that organizes elements and properties
                - [ ] Create class "series" (ie alkali earth metals, noble gases, etc)
                - [ ] Create a class for "element" that contains element data
                - [ ] Create table variants for isotopes, etc.
            - [ ] Atom Builder
                - [ ] First-Time Entrance Pop-Up
                - [ ] Create "proton" class
                - [ ] Create "neutron class
                - [ ] Create function that counts current protons and neutrons, and 
                checks for element, stability, etc.
            - [ ] Molecule Builder
                - [ ] Create class atom, that has property element, and valence
                - [ ] Function that attempts to name the created molecule
                - [ ] Function that attempts to define reactivity, etc
            - [ ] Reactions Lab
                - [ ] Define classes for different chemicals, stores properties
                - [ ] Create the function that does the reacting
                - [ ] Visualize! (Gases should look different from, liquids, solids, etc)
        - [ ] BioSpace
            - [ ] Tree of Life
                - [ ] Create classes for every level. I mean *every*, ***every*** level.
                - [ ] Create anatomical images and buttons/pop-ups, as well as facts, stats for every organism. ***EVERY*** organism. (Lol, this is going to be tough.)
            -  [ ] ALife
                - [x] Cellular Automata
                    - [x] Define a class for the cell engine, which has several toggles for neighborhoods, states, etc.
                    - [x] Define array for cells
                    - [x] Define the buffer array, to ensure all cells change at once.
                - [ ] Agent Systems
                    - [ ] Define a class for the agent, toggles for behaviors and such
                    - [ ] Create a world for the agent, which also has some toggles and controls
    - [ ] TechSPace
        - [ ] Sound + Sound Modulation
            - [x] Create a tone generator
            - [ ] Display the tone, albeit at a lower frequency
            - [ ] Create a proper function for modulation
            - [ ] Make it interactive
    - [ ] MathSpace
        - [ ] Randomness
            - [ ] Functions for different types of randomness
            - [ ] Create different visualizations of different kinds of randomness
        - [ ] Sequences + Series
            - [ ] Create a geometric sequence interpreter
            - [ ] Create an arithmetic sequence interpreter
            - [ ] Create a Collatz Conjecture lab
            - [ ] Create a metallics lab
            - [ ] Create a polygonate numbers lab
- [ ] Build app, with py2app!
