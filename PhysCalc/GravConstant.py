m1 = input("Mass of Object 1\n> ")
m2 = input("Mass of Object 2\n> ")
d = input("Distance between Objects\n> ")
F = input("Force between Objects\n> ")

# F = G * ((m1*m2)/d^2)
# G = F / ((m1*m2)/d^2)

G = float(F) / (float(m1*m2) / float(d**2))
print G
