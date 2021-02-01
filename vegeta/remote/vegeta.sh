#!/usr/bin/env bash

REQUESTS=( simple roll_times roll_operator notation cup custom )

createFolders() {
	echo "Creating results and reports folders"
	if [ -d "results" ]; then
	  rm -Rf results
	fi

	if [ -d "reports" ]; then
	  rm -Rf reports
	fi
	
	mkdir results
	mkdir reports
}

function download() {
	echo "Download Vegeta"
	wget -q https://github.com/tsenart/vegeta/releases/download/v12.8.4/vegeta_12.8.4_linux_amd64.tar.gz
	tar -xf vegeta_12.8.4_linux_amd64.tar.gz
	rm vegeta_12.8.4_linux_amd64.tar.gz
	rm README.md
	rm CHANGELOG
	rm LICENSE
}

function wakeUp() {
	echo "Waking up my application, because Heroku free needs to sleep"
	curl -i -H https://moviecountdowns-backend.herokuapp.com/api/countdowns/year/2021
	clear
}

function attack() {
	echo "Create requests for Dice Rolls $1"
	vegeta attack -name="Dice rolls - $1" -duration=30s -rate=100 -targets=requests/$1.request -output=results/$1.result
}

function report() {
	echo "Creating a report for Dice Rolls $1"
	vegeta report results/$1.result > reports/report_$1.txt
	cat reports/report_$1.txt
}

function reportHistogram() {
	echo "Creating an histogram for Dice Rolls $1"
	vegeta report -type='hist[0,20s,40s,60s,80s,100s]' results/$1.result > reports/report_histogram_$1.txt
	cat reports/report_histogram_$1.txt
}

function reportHDR() {
	echo "Creating an HDR report for Dice Rolls $1"
	vegeta report -type=hdrplot results/$1.result > reports/report_hdrplot_$1.txt
	cat reports/report_hdrplot_$1.txt
}

function reportJSON() {
	echo "Creating a JSON file for the Dice Rolls $1"
	vegeta report -type=json results/$1.result > reports/$1.json
}

function plot() {
	echo "Creating a graph of the results"
	vegeta plot -title="Dice rolls - $1" results/$1.result > reports/$1.html
	echo "Open file reports/$1.html on the browser"
}

function makeAPause() {
	read -p "Press ENTER to continue"
}

echo "Dragon Ball Z Based Performance tests!"
echo

createFolders
download
wakeUp

clear
echo "Attacking"
for REQUEST in "${REQUESTS[@]}"
do
	attack $REQUEST
done
makeAPause

clear
echo "Reports"
for REQUEST in "${REQUESTS[@]}"
do
	report $REQUEST
done
makeAPause

clear
echo "Histogram Reports"
for REQUEST in "${REQUESTS[@]}"
do
	reportHistogram $REQUEST
done
makeAPause

clear
echo "HDR Reports"
for REQUEST in "${REQUESTS[@]}"
do
	reportHDR $REQUEST
done
makeAPause

clear
echo "JSON Reports"
for REQUEST in "${REQUESTS[@]}"
do
	reportJSON $REQUEST
done
makeAPause

echo "Creating the graph"
for REQUEST in "${REQUESTS[@]}"
do
	plot $REQUEST
done

echo
echo "Tell Akira Toryiama if you are still watching DBZ in 2021!"
