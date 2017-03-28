#!/bin/sh
sudo kill -9 $(sudo lsof -t -i:2000)
sudo kill -9 $(sudo lsof -t -i:2001)
sudo kill -9 $(sudo lsof -t -i:2002)
sudo kill -9 $(sudo lsof -t -i:2003)

sudo kill -9 $(sudo lsof -t -i:4000)
sudo kill -9 $(sudo lsof -t -i:4001)
sudo kill -9 $(sudo lsof -t -i:4002)
sudo kill -9 $(sudo lsof -t -i:4003)

sudo kill -9 $(sudo lsof -t -i:6000)
sudo kill -9 $(sudo lsof -t -i:6001)
sudo kill -9 $(sudo lsof -t -i:6002)
sudo kill -9 $(sudo lsof -t -i:6003)


