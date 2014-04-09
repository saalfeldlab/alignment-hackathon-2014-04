#!/bin/bash

# Run me like this:
# qsub -N fiji.archipelago -V -o fiji.archipelago -pe batch 16 `pwd`/fijigo.sh

/nobackup/hackathon/fiji.archipelago/Fiji.app/fiji --full-classpath --main-class edu.utexas.clm.archipelago.Fiji_Archipelago h01u01 4012
