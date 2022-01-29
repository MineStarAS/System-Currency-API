package kr.kro.minestar.utility.location

import org.bukkit.Location

enum class Axis {
    X,
    Y,
    Z,
    YAW,//좌우
    PITCH//상하
}

/**
 * Location의 axis에 number를 더합니다.
 */
fun Location.addAxis(axis: Axis, number: Number): Location {
    if (number == 0) return this
    when (axis) {
        Axis.X -> add(number.toDouble(), 0.0, 0.0)
        Axis.Y -> add(0.0, number.toDouble(), 0.0)
        Axis.Z -> add(0.0, 0.0, number.toDouble())
        Axis.YAW -> yaw += number.toFloat()
        Axis.PITCH -> {
            var n = number.toFloat()
            if (0 < n) {
                while (n !in -360F..360F) n -= 360F
                val sum = pitch + n
                if (sum in -90F..90F) {
                    pitch += n
                    return this
                }
                if (sum > 90F) {
                    turnBack()
                    pitch = 90F - (sum - 90F)
                }
            }
            if (0 > n) {
                while (n !in -360F..360F) n += 360F
                val sum = pitch + n
                if (sum in -90F..90F) {
                    pitch += n
                    return this
                }
                if (sum < -90F) {
                    turnBack()
                    pitch = -90F - (sum + 90F)
                }
            }
        }
    }
    return this
}

/**
 * Location의 axis를 number로 설정합니다.
 */
fun Location.setAxis(axis: Axis, number: Number): Location {
    when (axis) {
        Axis.X -> x = number.toDouble()
        Axis.Y -> y = number.toDouble()
        Axis.Z -> z = number.toDouble()
        Axis.YAW -> yaw = number.toFloat()
        Axis.PITCH -> pitch = number.toFloat()
    }
    return this
}

/**
 * Location의 yaw 좌표가 정 반대를 보게 설정합니다.
 */
fun Location.turnBack(): Location {
    yaw += 180
    return this
}

/**
 * Location의 블럭 중심좌표로 변환합니다.
 */
fun Location.toCenter(): Location {
    x = x.toInt().toDouble() + 0.5
    y = y.toInt().toDouble() + 0.5
    z = z.toInt().toDouble() + 0.5
    return this
}

/**
 * Location의 블럭 바닥중심좌표로 변환합니다.
 */
fun Location.toFloorCenter(): Location {
    x = x.toInt().toDouble() + 0.5
    y = y.toInt().toDouble()
    z = z.toInt().toDouble() + 0.5
    return this
}

/**
 * Location이 두 입력 Location 사이에 있는지 구분합니다.
 */
fun Location.isInside(loc1: Location, loc2: Location): Boolean {
    if (world != loc1.world) return false
    if (world != loc2.world) return false
    val xLow: Int
    val xHigh: Int
    val yLow: Int
    val yHigh: Int
    val zLow: Int
    val zHigh: Int

    if (loc1.blockX <= loc2.blockX) {
        xLow = loc1.blockX
        xHigh = loc2.blockX
    } else {
        xLow = loc2.blockX
        xHigh = loc1.blockX
    }
    if (loc1.blockY <= loc2.blockY) {
        yLow = loc1.blockY
        yHigh = loc2.blockY
    } else {
        yLow = loc2.blockY
        yHigh = loc1.blockY
    }
    if (loc1.blockZ <= loc2.blockZ) {
        zLow = loc1.blockZ
        zHigh = loc2.blockZ
    } else {
        zLow = loc2.blockZ
        zHigh = loc1.blockZ
    }

    if (blockX !in xLow..xHigh) return false
    if (blockY !in yLow..yHigh) return false
    if (blockZ !in zLow..zHigh) return false
    return true
}

/**
 * Location이 중심 Location을 기준으로 형성된 직육면체 내에 있는지 구분합니다.
 */
fun Location.isInsideToCube(location: Location, halfLength: Number): Boolean {
    if (world != location.world) return false
    val l = halfLength.toInt()
    val loc1 = Location(world, location.x - l, 0.0, location.z - l).toBlockLocation()
    val loc2 = Location(world, location.x + l, 256.0, location.z + l).toBlockLocation()
    val xLow: Int
    val xHigh: Int
    val yLow: Int
    val yHigh: Int
    val zLow: Int
    val zHigh: Int

    if (loc1.blockX <= loc2.blockX) {
        xLow = loc1.blockX
        xHigh = loc2.blockX
    } else {
        xLow = loc2.blockX
        xHigh = loc1.blockX
    }
    if (loc1.blockY <= loc2.blockY) {
        yLow = loc1.blockY
        yHigh = loc2.blockY
    } else {
        yLow = loc2.blockY
        yHigh = loc1.blockY
    }
    if (loc1.blockZ <= loc2.blockZ) {
        zLow = loc1.blockZ
        zHigh = loc2.blockZ
    } else {
        zLow = loc2.blockZ
        zHigh = loc1.blockZ
    }

    if (blockX !in xLow..xHigh) return false
    if (blockY !in yLow..yHigh) return false
    if (blockZ !in zLow..zHigh) return false
    return true
}

/**
 * Location이 입력 Location으로부터 distance내에 있는지 구분합니다.
 */
fun Location.isInRange(loc: Location, distance: Number): Boolean {
    if (world != loc.world) return false
    if (distance.toDouble() < distance(loc)) return false
    return true
}

/**
 * Location의 시점 좌표가 입력 Location을 바라보게 설정합니다.
 */
fun Location.look(loc: Location): Location {
    direction = loc.subtract(this).toVector()
    return this
}

/**
 * Location의 yaw와 pitch를 영점으로 설정합니다.
 */
fun Location.directionZero(): Location {
    yaw = 0F
    pitch = 0F
    return this
}

/**
 * Location 의 보는 방향으로 length 값 만큼 offset 시킵니다.
 */
fun Location.offset(length: Number) = toVector().add(direction.multiply(length.toDouble())).toLocation(world)

