# Route Cipher

Route Cipher is a transposition cipher where the key is which route to follow when reading the
ciphertext from the block created with the plaintext. The plaintext is written in a grid, and then read off
following the route chosen.

**Encryption()** 

First we write the plaintext in a block of reasonable size for the plaintext. Part of your key is the size of this
grid, so you need to decide on either a number of columns or number of rows in the grid before starting.
Once the characters of the plaintext are written out sequentially out in the grid, you use the Route
assigned. This could be spiraling inwards starting from the top left corner in a counterclockwise direction
in case the key is positive, or spiraling inwards starting from the bottom right corner in a counterclockwise
direction in case the key is negative.

As an example, lets encrypt the plaintext **"ABORT THE MISSION, YOU HAVE BEEN SPOTTED"**.

With key -5 encryption should be : **“XTEANITROBATSYVNTEDXOEHOMEHSOESPBUI”**.

With key 5 encryption should be : **"ATSYVNTEDXXTEANITROBHSOESPOEHOMEIUB”**.


**Decryption()**

To decrypt a message received that has been encoded with the Route Cipher, we need to know the route
used and the width or height of the grid. We then start by constructing a blank grid of the right size, and
then place the ciphertext letters in the grid following the route specified.
For example, to decrypt the ciphertext **"TIEIXTXXEAHSIHSPNTLT"** with the route spiral inwards
counterclockwise from the top right, with a grid width of 4, we follow the process shown above to obtain
the text **THISISTHEPLAINTEXT**.

**For the sake of showing the algorithm I am going to use only arrays**
