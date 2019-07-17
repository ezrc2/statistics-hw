treatment <- c(185, 163, 125, 151, 101)
control <- c(190, 175, 178, 135, 201)
boxplot(control, treatment, main = "Blood Sugar Levels", names = c("control", "treatment"), ylab = "Blood Sugar Level (mg/dL)")

v <- c(22.8, 30.8, 4.8, 40.0, 29.2, 13.6, 24.0, 35.6, 11.2, 8.8, 0.4, 6.0, 13.6, 6.4, 32.4, 29.2, 30.8, 6.8, 10.8, 35.2, 13.6, 32.4, 32.4, 20.0, 13.6)
barplot(v, main = "Mean Differences", xlab = "Trial #", ylab = "Difference", names.arg = c(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25))
abline(h = 30.8, col = "blue")
