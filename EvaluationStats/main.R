mydata <- read.csv("../data/loginData.csv")
attach(mydata)
print(mydata)

hist(Logins.Total, ylim=c(0,20),breaks=7, main="Histogram of Total Logins", xlab = "Total logins")

hist(Logins.success, ylim=c(0,25), xlim=c(0,25), main="Histogram of Succesful Logins", xlab = "Succesful logins")

hist(Logins.Failure, ylim=c(0,25), xlim=c(0,25), main="Histogram of Failed Logins", xlab = "Failed logins")

hist(Logins.avgFail, ylim=c(0,25), xlim=c(0,60000), main="Histogram of time for Failed Logins", xlab = "time of Failed logins (ms)")

hist(Logins.avgSuccess, ylim=c(0,25), xlim=c(0,50000), main="Histogram of time for Successful Logins", xlab = "time of Successful logins (ms)")

