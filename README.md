# Calculate-Pi-Monte-Carlo

<p>The value of &pi; can be approximated using what's known as a "Monte Carlo Simulation." The idea is to inscribe a circle inside a square and
randomly place points in the square where some will land in the circle and some won't. As more points are placed, the ratio between
the points in the circle and the total number of points (those both in and outside the circle) will come arbitrarily close to &pi;/4 
then we can multiply by 4 to obtain &pi;.</p> 

Here's how it works. The area of a circle is &pi;r<sup>2</sup> and the area of a square is 4r<sup>2</sup> i.e. 2r * 2r so the ratio of the area of the circle to the area of the square is &pi;r<sup>2</sup>/4r<sup>2</sup> = &pi;/4 and we can approximate the area of the circle by the number of points that land in it and same for the area of the square. So all thats left to do is multiply by 4 to get &pi;.
