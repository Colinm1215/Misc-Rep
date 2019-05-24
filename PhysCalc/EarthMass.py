A = input("Altitude")
R = (A*1000.0) + (6.4*10**6)
print (A*1000.0)
print (R)
S = input("Speed")
V = (S*1000.0)/3600.0
print (V)

M = (V**2 * R) / (6.67*10**-11)

print M