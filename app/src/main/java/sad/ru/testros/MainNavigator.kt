package sad.ru.testros

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import sad.ru.testros.activity.main.MainActivity
import sad.ru.testros.base.BaseFragment

internal class MainNavigator(
    activity: MainActivity,
    containerId: Int
) : SupportAppNavigator(activity, containerId) {

    private val localActivity = activity

    override fun applyCommands(commands: Array<Command>) {
        super.applyCommands(commands)
    }

    override fun createFragment(screen: SupportAppScreen?): Fragment {
        localActivity.showBottomMenu((screen?.fragment as BaseFragment).isShowBottomMenu())
        return screen.fragment
    }

    override fun setupFragmentTransaction(
        command: Command?,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction?
    ) {
        fragmentTransaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    }
}