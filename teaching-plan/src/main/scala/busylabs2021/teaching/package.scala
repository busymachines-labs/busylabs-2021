package busylabs2021

package object teaching {
  type Title = Title.Type
  object Title extends Sprout[String]

  type Description = Description.Type
  object Description extends Sprout[String]
}
