#!/bin/sh

javac -d ../bin Tagger.java
java ../bin/Tagger ../inputskels/fsm-input.utf8.txt $1 