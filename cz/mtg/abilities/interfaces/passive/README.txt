Package information:

Interfaces in this package are for abilities, that trigger automatically, or add some special functionality,
which behaves independently of the player.
That means, we have to check for their presence in cards when performing certain operations.
Abilities, that do this, MUST IMPLEMENT the interfaces contained here, if an appropriate interface is present,
otherwise it has to be created and corresponding methods edited.

Each keyWord here mirrors the part of card this interface modifies
AffectsDestroying --> modifies what happens when a permanent has to be destroyed