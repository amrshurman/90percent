mydata <- read.csv("../data/loginData.csv")
attach(mydata)
print(mydata)

hist(Logins.Total, ylim=c(0,20),breaks=7, main="Histogram of Total Logins", xlab = "Total logins")

hist(Logins.success, ylim=c(0,25), xlim=c(0,25), main="Histogram of Succesful Logins", xlab = "Succesful logins")

hist(Logins.Failure, ylim=c(0,25), xlim=c(0,25), main="Histogram of Failed Logins", xlab = "Failed logins")

