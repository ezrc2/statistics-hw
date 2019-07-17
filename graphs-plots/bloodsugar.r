treatment <- c(185, 163, 125, 151, 101)
control <- c(190, 175, 178, 135, 201)
boxplot(control, treatment, main = "Blood Sugar Levels", names = c("control", "treatment"), ylab = "Blood Sugar Level (mg/dL)")
