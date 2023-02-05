#!/bin/sh

javac -d ../bin Tagger.java
java Tagger ../inputskels/fsm-input.utf8.txt $1 