package jaysonjson.papierfuchs.fuchs.io.data

import org.bukkit.util.Vector

class FuchsSize {
    var x = 0.0
    var y = 0.0
    var z = 0.0

    constructor()
    constructor(x: Double, y: Double, z: Double) {
        this.x = x
        this.y = y
        this.z = z
    }

    fun vector(): Vector {
        return Vector(x, y, z)
    }
}