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
fun Location.add(axis: Axis, number: Number): Location {
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
fun Location.set(axis: Axis, number: Number): Location {
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
    val xLow: Double
    val xHigh: Double
    val yLow: Double
    val yHigh: Double
    val zLow: Double
    val zHigh: Double

    if (loc1.x <= loc2.x) {
        xLow = loc1.x
        xHigh = loc2.x
    } else {
        xLow = loc2.x
        xHigh = loc1.x
    }
    if (loc1.y <= loc2.y) {
        yLow = loc1.y
        yHigh = loc2.y
    } else {
        yLow = loc2.y
        yHigh = loc1.y
    }
    if (loc1.z <= loc2.z) {
        zLow = loc1.z
        zHigh = loc2.z
    } else {
        zLow = loc2.z
        zHigh = loc1.z
    }

    if (x !in xLow..xHigh) return false
    if (y !in yLow..yHigh) return false
    if (z !in zLow..zHigh) return false
    return true
}

/**
 * Location이 중심 Location을 기준으로 형성된 직육면체 내에 있는지 구분합니다.
 */
fun Location.isInsideToCube(center: Location, halfLength: Number): Boolean {
    if (world != center.world) return false
    val l = halfLength.toDouble()
    val loc1 = Location(world, center.x - l, 0.0, center.z - l)
    val loc2 = Location(world, center.x + l, 256.0, center.z + l)
    val xLow: Double
    val xHigh: Double
    val yLow: Double
    val yHigh: Double
    val zLow: Double
    val zHigh: Double

    if (loc1.x <= loc2.x) {
        xLow = loc1.x
        xHigh = loc2.x
    } else {
        xLow = loc2.x
        xHigh = loc1.x
    }
    if (loc1.y <= loc2.y) {
        yLow = loc1.y
        yHigh = loc2.y
    } else {
        yLow = loc2.y
        yHigh = loc1.y
    }
    if (loc1.z <= loc2.z) {
        zLow = loc1.z
        zHigh = loc2.z
    } else {
        zLow = loc2.z
        zHigh = loc1.z
    }

    if (x !in xLow..xHigh) return false
    if (y !in yLow..yHigh) return false
    if (z !in zLow..zHigh) return false
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
fun Location.lookAtTheLocation(loc: Location): Location {
    direction = loc.subtract(this).toVector()
    return this
}