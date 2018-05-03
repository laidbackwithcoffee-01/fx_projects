#!/bin/sh

bckdir="/home/kenichi/backup/"
bcfile="/home/kenichi/backup/pairsprice_"
sqlfile="/home/kenichi/sql/truncate_pairsprice.sql"

# create bacup directory
if [ ! -e $bckdir ]
then
    mkdir $bckdir
fi

# pg_dump postgresql fx_projects pairsprice
now="$( date +"%Y%m%d%H%M%S" )"
pg_dump --username=dbuser --data-only --table public.pairsprice fx_projects > $bcfile$now

if [ $? -eq 0 ]; then
    # truncate pairsprice table
    psql -f $sqlfile -U dbuser -d fx_projects -h localhost

    if [ $? -eq 0 ]; then
        # LineNotifier
        python3 /home/kenichi/bin/backupandclearNotifier.py
    fi
fi
