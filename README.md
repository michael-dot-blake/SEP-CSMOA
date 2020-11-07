# Jemena Simulation
This is a git repository created for the capstone project for Software Engineering Final Project at RMIT semester 2 2020. The project was to build a digital twin
for the Australian gas provider Jemena. This simulation would take in an amount of workers for the day (Gas Servicing Technicians, 
abbreviated to GSTs) as well as take in a list of jobs and their locations based on historical data. The jobs are added to the pool based on their time and are paired
up with an available worker. 

The simulation calls the Microsoft Azure Maps API to calculate the travel time from the position of the worker to the location of the job.
Once the last job in the simulation is completed and all workers have returned to their starting positions, the program will terminate and separate output files will be
created in .csv formats for Job statistics and Worker statistics. This data includes individual data on each job as well as daily statistics for each worker on shift.

## Setup
This project was developed using Java 1.8 (Java 8) and will require installation of the Java Runtime Environment in order to run
and the Java Development Kit to contribute to. The relevant libraries: JTS Topology Suite, OpenCSV, and GSON; are all included in 
the /lib/ folder of the repository. They should be added to the project build path using an IDE such as IntelliJ IDEA or Eclipse.

## Contribution
This project is the intellectual Property of Jemena and all development work on this project should be conducted with their explicit consent.
The students who contributed to this project were:
* Clark Skinner
* Michael Blake
* Sawmya Patidar
* Arif Hasan
* Olivia Wang

## Project Status
Development of this project has currently ceased. The current project exists as a prototype for what a final shippable product may look like.



