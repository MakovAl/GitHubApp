package com.makoval.githubapp

import android.util.Base64
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.makoval.githubapp.login.LoginFragmentDirections
import com.makoval.githubapp.network.Repositories
import com.makoval.githubapp.login.ReposApiStatus
import com.makoval.githubapp.repositories.RepositoriesAdapter
import io.noties.markwon.Markwon
import kotlinx.android.synthetic.main.login_fragment.view.*

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Repositories>?) {
    val adapter = recyclerView.adapter as RepositoriesAdapter
    adapter.submitList(data)
}

@BindingAdapter("linkRepository")
fun bindLinkRepository(textView: TextView, link_repository: String?) {
    link_repository.let {
        val link = link_repository?.replace("https://", "")
        textView.text = link
        textView.linksClickable = true
    }
}


@BindingAdapter("detailLicense")
fun bindDetailLicense(textView: TextView, detailLicense: String?) {
    detailLicense.let {
        if (detailLicense.isNullOrEmpty()) {
            textView.text = "No"
        } else {
            textView.text = detailLicense
        }
    }
}

@BindingAdapter("detailStargazers")
fun bindDetailStargazers(textView: TextView, detailStargazers: Int?) {
    detailStargazers.let {
        textView.text = detailStargazers.toString()
    }
}

@BindingAdapter("detailForks")
fun bindDetailForks(textView: TextView, detailForks: Int?) {
    detailForks.let {
        textView.text = detailForks.toString()
    }
}

@BindingAdapter("detailWatchers")
fun bindDetailWatchers(textView: TextView, detailWatchers: Int?) {
    detailWatchers.let {
        textView.text = detailWatchers.toString()
    }
}

@BindingAdapter("content")
fun bindContent(textView: TextView, content: String?) {
    content.let {
        if (content != null) {
            val remEnd = it?.replace("\n", "")
            val decode = Base64.decode(remEnd, Base64.DEFAULT).decodeToString()
            val markwon = Markwon.create(textView.context)
            markwon.setMarkdown(textView, decode)
        }
    }
}

@BindingAdapter("reposApiStatus")
fun bindStatus(progressBar: ProgressBar, status: ReposApiStatus?) {
    when (status) {
        ReposApiStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        ReposApiStatus.ERROR -> {
            progressBar.visibility = View.GONE
        }
        ReposApiStatus.DONE -> {
        }
    }
}


@BindingAdapter("constraintLayout")
fun bindConstraintLayout(layout: ConstraintLayout, status: ReposApiStatus?) {
    when (status) {
        ReposApiStatus.LOADING -> {
        }
        ReposApiStatus.ERROR -> {
            layout.text_personal_access_token.setTextColor(
                ContextCompat.getColor(
                    layout.context,
                    R.color.red
                )
            )
            DrawableCompat.setTint(
                layout.personal_access_token.background,
                ContextCompat.getColor(layout.context, R.color.red)
            )
        }
        ReposApiStatus.DONE -> {
            layout.findNavController().navigate(
                LoginFragmentDirections.actionLoginToRepositories(
                    layout.personal_access_token.text.toString()
                )
            )
        }
    }
}
